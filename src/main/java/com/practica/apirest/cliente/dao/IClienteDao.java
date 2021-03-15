package com.practica.apirest.cliente.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.practica.apirest.cliente.models.entity.Cliente;
import com.practica.apirest.cliente.models.entity.Region;

public interface IClienteDao extends JpaRepository<Cliente, Long>{
	
	@Query("from Region")
	public List<Region> findAllRegiones();

}
