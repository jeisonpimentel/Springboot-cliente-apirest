package com.practica.apirest.cliente.service;

import com.practica.apirest.cliente.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);

}
