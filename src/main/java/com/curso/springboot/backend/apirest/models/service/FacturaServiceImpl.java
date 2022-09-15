package com.curso.springboot.backend.apirest.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.backend.apirest.models.dao.IFacturaDao;
import com.curso.springboot.backend.apirest.models.entity.Factura;

@Service
public class FacturaServiceImpl implements IFacturaService{

	@Autowired
	IFacturaDao facturaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		List<Factura> facturas = new ArrayList<>();
		facturaDao.findAll().forEach(f->{
			facturas.add(f);
		});
		return facturas;
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional
	public Factura save(Factura factura) {
		// TODO Auto-generated method stub
		return facturaDao.save(factura);
	}	

}
