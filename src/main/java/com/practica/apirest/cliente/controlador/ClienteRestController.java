package com.practica.apirest.cliente.controlador;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.practica.apirest.cliente.models.entity.Cliente;
import com.practica.apirest.cliente.models.entity.Region;
import com.practica.apirest.cliente.service.IClienteService;

@CrossOrigin(origins = "http://localhost:4401")
@RestController
@RequestMapping("/api")
public class ClienteRestController { 
	
	@Autowired
	private IClienteService clienteService;
	
	private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);
	
	@GetMapping("/clientes")
	public List<Cliente> index()
	{
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page)
	{
		Pageable pageable = PageRequest.of(page, 5); 
		return clienteService.findAll(pageable);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id)
	{
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try
		{
			cliente = clienteService.findById(id);
		}
		catch(DataAccessException ex)
		{
			response.put("mensaje", "'Error al realizar la consulta en la base de datos!");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		if(cliente == null)
		{
			response.put("mensaje", "¡El cliente con ID: " + id + " NO existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("cliente", cliente);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result)
	{
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try
		{
			clienteNew = clienteService.save(cliente);
		}
		catch(DataAccessException ex)
		{
			response.put("mensaje", "'Error al realizar el insert en la base de datos!");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "Cliente insetado con exito en la base de datos");
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id)
	{
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors())
		{
			List<String> errors = result.getFieldErrors()
					.stream().map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(clienteActual == null)
		{
			response.put("mensaje", "Error: ¡El cliente con ID: " + id + " NO existe en la base de datos!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try
		{
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setCreateAt(cliente.getCreateAt());;
			clienteActual.setRegion(cliente.getRegion());
			
			clienteActualizado = clienteService.save(clienteActual);
		} 
		catch(DataAccessException ex)
		{
			response.put("mensaje", "'Error al realizar el update en la base de datos!");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("mensaje", "El cliente ha sido actualizado correctamente!");
		response.put("clienteActualizado", clienteActualizado);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) 
	{
		
		Map<String, Object> response = new HashMap<>();
		try
		{
			Cliente cliente = clienteService.findById(id);
			String lastFoto = cliente.getFoto();
			
			if(lastFoto != null && lastFoto.length() > 0)
			{
				Path rutaFotoAnterior = Paths.get("uploads").resolve(lastFoto).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead())
				{
					archivoFotoAnterior.delete();
				}
			}
			
			clienteService.deleteById(id);
		}
		catch(DataAccessException ex)
		{
			response.put("mensaje", "'Error al eliminara al cliente en la base de datos!");
			response.put("error", ex.getMessage().concat(": ").concat(ex.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Cliente eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id)
	{
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
		
		if(!archivo.isEmpty())
		{
			String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try 
			{
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} 
			catch (IOException ex) 
			{
				response.put("mensaje", "'Error al cargar imagen en la base de datos");
				response.put("error", ex.getMessage().concat(": ").concat(ex.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			String lastFoto = cliente.getFoto();
			
			if(lastFoto != null && lastFoto.length() > 0)
			{
				Path rutaFotoAnterior = Paths.get("uploads").resolve(lastFoto).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead())
				{
					archivoFotoAnterior.delete();
				}
			}
			
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "Ha subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto)

	{
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		Resource recurso = null;
		
		try 
		{
			recurso = new UrlResource(rutaArchivo.toUri());
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		if(!recurso.exists() && !recurso.isReadable())
		{
			rutaArchivo = Paths.get("src/main/resources/static/image").resolve("usuario.png").toAbsolutePath();
			
			try 
			{
				recurso = new UrlResource(rutaArchivo.toUri());
			} 
			catch (MalformedURLException e) 
			{
				e.printStackTrace();
			}
			
			log.error("¡Ha ocurrido un problema al momento de cargar la imagen!");
		}
		
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones() 
	{
		return clienteService.findAllRegiones();
	}
	
}