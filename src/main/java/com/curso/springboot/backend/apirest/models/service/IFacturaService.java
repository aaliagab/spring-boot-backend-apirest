package com.curso.springboot.backend.apirest.models.service;

import java.util.List;

import com.curso.springboot.backend.apirest.models.entity.Factura;

public interface IFacturaService {

	public List<Factura> findAll();
	
	public Factura findById(Long id);
	
	public void delete(Long id);
	
	public Factura save(Factura factura); 
}
