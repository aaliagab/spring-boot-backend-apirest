package com.curso.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.backend.apirest.models.entity.Factura;
import com.curso.springboot.backend.apirest.models.entity.Producto;
import com.curso.springboot.backend.apirest.models.service.IFacturaService;
import com.curso.springboot.backend.apirest.models.service.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200", "*"}) //"*" acepta peticiones de cualquir origen, util para produccion
@RestController
@RequestMapping("/api")
public class FacturaRestController {

	@Autowired
	IFacturaService facturaService;
	
	@Autowired
	IProductoService productoService;
	
	/*@GetMapping("/facturas")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Factura> listadoFacturas(){
		return facturaService.findAll();
	}*/
	
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@GetMapping("/facturas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Factura show(@PathVariable(name = "id") Long id) {
		return facturaService.findById(id);
	}
	
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/facturas")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Factura crear(@RequestBody Factura factura) {
		return facturaService.save(factura);
	}
	
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/facturas/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		facturaService.delete(id); 
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/facturas/filtrar-productos/{term}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Producto> listadoProductosMatch(@PathVariable String term){
		return productoService.findByNombre(term);
	}
	
	
}
