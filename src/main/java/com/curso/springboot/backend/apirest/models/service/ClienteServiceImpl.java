package com.curso.springboot.backend.apirest.models.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.backend.apirest.models.dao.IClienteDao;
import com.curso.springboot.backend.apirest.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{
	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly = true)//es opcional pues en CrudRepository ya los metodos son transaccionales
	//de todos modos asi estamos sobrescribiendo a nuestro gusto el Transactional
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}
	
}
