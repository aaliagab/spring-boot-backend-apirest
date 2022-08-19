package com.curso.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.curso.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	//esta variante es por nombre de metodo segun Spring Data con JPA
	//caso de ser mas de un parametro se separa con And o Or segun corresponde
	//ejemplo buscar por username y email seria findByUsernameAndEmail(String username, String email)
	public Usuario findByUsername(String username);
	
	//variante usando Query de JPA
	@Query("select u from Usuario u where u.username=?1") //1 indica primer parametro	
	public Usuario findByUsername2(String username);
	//si fuera tambien el paraetro email
	//@Query("select u from Usuario u where u.username=?1 and u.email=?2")
}
