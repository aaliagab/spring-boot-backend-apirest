package com.curso.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.backend.apirest.models.entity.Cliente;
import com.curso.springboot.backend.apirest.models.service.IClienteService;

//compartiendo recursos de nuestro api rest al dominio de app angular mediante uso de cors
//tambien es posible restringir otros parametros
@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
}
