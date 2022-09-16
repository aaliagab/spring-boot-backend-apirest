package com.curso.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.backend.apirest.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%")
	//%?1% indica que el primer parametro texto este en cualquier parte del nombre
	public List<Producto> findByNombre(String term);
	
	//Es otra variante para querys a traves del nombre de metodo
	//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
	public List<Producto> findByNombreContainingIgnoreCase(String term);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String term);
}
