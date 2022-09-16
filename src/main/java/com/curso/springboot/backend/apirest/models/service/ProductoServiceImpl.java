package com.curso.springboot.backend.apirest.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.backend.apirest.models.dao.IProductoDao;
import com.curso.springboot.backend.apirest.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	IProductoDao productoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		// TODO Auto-generated method stub
		return productoDao.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombreContainingIgnoreCase(String term) {
		// TODO Auto-generated method stub
		return productoDao.findByNombreContainingIgnoreCase(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findByNombreStartingWithIgnoreCase(String term) {
		// TODO Auto-generated method stub
		return productoDao.findByNombreStartingWithIgnoreCase(term);
	}
	

}
