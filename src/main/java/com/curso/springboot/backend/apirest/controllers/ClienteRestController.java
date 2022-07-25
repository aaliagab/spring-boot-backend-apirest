package com.curso.springboot.backend.apirest.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/clientes/{id}")
	public Cliente showById(@PathVariable Long id){
		return clienteService.findById(id);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)//si sale bien retorna 201, si no se coloca por 
	//defecto retorna OK con codigo 200 si todo sale bien	
	public Cliente create(@RequestBody Cliente cliente){
		return clienteService.save(cliente);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente cliente_actual = clienteService.findById(id);
		cliente_actual.setApellidos(cliente.getApellidos());
		cliente_actual.setEmail(cliente.getEmail());
		cliente_actual.setNombre(cliente.getNombre());
		return clienteService.save(cliente_actual);
	}
	
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		clienteService.delete(id);
	}
}
