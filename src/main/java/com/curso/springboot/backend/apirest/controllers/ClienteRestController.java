package com.curso.springboot.backend.apirest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> index(){//Tratamiento de errores
		Map<String, Object> response = new HashMap<>();
		List<Cliente> clientes = null;
		try {
			clientes = clienteService.findAll();
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(clientes == null || clientes.size()==0) {
			response.put("mensaje", "No existen clientes registrados en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> showById(@PathVariable Long id){//Manejo de errores en backend
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(cliente == null) {			
			response.put("mensaje", "El cliente ID: ".
					concat(id.toString()).
					concat(" no exite en la base de datos!!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)//si sale bien retorna 201, si no se coloca por 
	//defecto retorna OK con codigo 200 si todo sale bien	
	public ResponseEntity<?> create(@RequestBody Cliente cliente){
		Cliente nuevo_cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			nuevo_cliente = clienteService.save(cliente);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(nuevo_cliente == null) {
			response.put("mensaje", "No fue posible crear el nuevo cliente contacte con el administrador");
			new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		response.put("mensaje", "Cliente creado con éxito!!");
		response.put("cliente", nuevo_cliente);
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente cliente_actual = clienteService.findById(id), cliente_actualizado = null;
		Map<String, Object> response = new HashMap<>();
		if(cliente_actual == null) {			
			response.put("mensaje", "El cliente ID: ".
					concat(id.toString()).
					concat(" no exite en la base de datos!!"));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {			
			cliente_actual.setApellidos(cliente.getApellidos());
			cliente_actual.setEmail(cliente.getEmail());
			cliente_actual.setNombre(cliente.getNombre());
			cliente_actualizado = clienteService.save(cliente_actual);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente actualizado con éxito!!");
		response.put("cliente", cliente_actualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){		
		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado con éxito!!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
