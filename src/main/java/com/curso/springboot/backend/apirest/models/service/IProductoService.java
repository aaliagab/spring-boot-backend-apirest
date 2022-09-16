package com.curso.springboot.backend.apirest.models.service;

import java.util.List;

import com.curso.springboot.backend.apirest.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findByNombre(String term);
	
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String term);
}
