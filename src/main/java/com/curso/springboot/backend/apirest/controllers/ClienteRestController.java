package com.curso.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.curso.springboot.backend.apirest.models.entity.Cliente;
import com.curso.springboot.backend.apirest.models.entity.Region;
import com.curso.springboot.backend.apirest.models.service.IClienteService;
import com.curso.springboot.backend.apirest.models.service.IUploadFileService;

//compartiendo recursos de nuestro api rest al dominio de app angular mediante uso de cors
//tambien es posible restringir otros parametros
@CrossOrigin(origins = {"http://localhost:4200"})

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadFileService uploadFileService;

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
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(clientes == null || clientes.size()==0) {
			response.put("mensaje", "No existen clientes registrados en la base de datos");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	@GetMapping("/clientes/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page){
		Map<String, Object> response = new HashMap<>();
		Page<Cliente> clientes = null;
		try {
			clientes = clienteService.findAll(PageRequest.of(page, 4));
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "No fue posible obtener los datos de la BD en estos momentos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
		}
		if(clientes == null || clientes.getContent().size()==0) {
			response.put("mensaje", "No existen clientes registrados en la base de datos para la pagina "+page);
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clientes, HttpStatus.OK);

	}

	//@Secured esta comentado solo para etapa de desarrollo hacer testing con todos los permisos
	//@Secured({"ROLE_USER","ROLE_ADMIN"})
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
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(cliente == null) {
			response.put("mensaje", "El cliente ID: ".
					concat(id.toString()).
					concat(" no exite en la base de datos!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
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
			response.put("mensaje", "Error al crear el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(nuevo_cliente == null) {
			response.put("mensaje", "No fue posible crear el nuevo cliente contacte con el administrador");
			new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		response.put("mensaje", "Cliente creado con éxito!!");
		response.put("cliente", nuevo_cliente);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@RequestBody Cliente cliente, @PathVariable Long id){
		Cliente cliente_actual = clienteService.findById(id), cliente_actualizado = null;
		Map<String, Object> response = new HashMap<>();
		if(cliente_actual == null) {
			response.put("mensaje", "El cliente ID: ".
					concat(id.toString()).
					concat(" no exite en la base de datos!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			cliente_actual.setApellidos(cliente.getApellidos());
			cliente_actual.setEmail(cliente.getEmail());
			cliente_actual.setNombre(cliente.getNombre());
			cliente_actual.setRegion(cliente.getRegion());
			cliente_actualizado = clienteService.save(cliente_actual);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente actualizado con éxito!!");
		response.put("cliente", cliente_actualizado);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		try {
			Cliente cliente = clienteService.findById(id);
			String archivoAnterior = cliente.getFoto();
			uploadFileService.eliminar(archivoAnterior);
			clienteService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Cliente eliminado con éxito!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo,
			@RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		Cliente cliente = clienteService.findById(id);
		if(!archivo.isEmpty()) {
			String nombreArchivo = null;

			try {
				nombreArchivo = uploadFileService.copiar(archivo, id);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				response.put("mensaje", "Error al subir la imagen al servidor");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String archivoAnterior = cliente.getFoto();
			uploadFileService.eliminar(archivoAnterior);

			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "La imagen "+nombreArchivo+" se ha subido correctamente");
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/uploads/img/{nombreArchivo:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreArchivo){

		Resource recurso = null;
		try {
			recurso = uploadFileService.cargar(nombreArchivo);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename =\""+recurso.getFilename()+"\"");

		return new ResponseEntity<>(recurso,cabecera, HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public ResponseEntity<?> regiones(){//Tratamiento de errores
		Map<String, Object> response = new HashMap<>();
		List<Region> regiones = null;
		try {
			regiones = clienteService.findAllRegiones();
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("mensaje", "Error al realizar la consulta a la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(regiones == null || regiones.size()==0) {
			response.put("mensaje", "No existen regiones registradas en la base de datos");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(regiones, HttpStatus.OK);
		
	}

}
