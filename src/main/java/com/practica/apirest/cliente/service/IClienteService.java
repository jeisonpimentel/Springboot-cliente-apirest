package com.practica.apirest.cliente.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.practica.apirest.cliente.models.entity.Cliente;
import com.practica.apirest.cliente.models.entity.Region;

public interface IClienteService {
	
	 public List<Cliente> findAll();
	 
	 public Page<Cliente> findAll(Pageable pageable);
	 
	 public Cliente findById(Long id);
	 
	 public Cliente save(Cliente cliente);
	 
	 public void deleteById(Long id);
	 
	 public List<Region> findAllRegiones();

}
