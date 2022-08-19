package com.curso.springboot.backend.apirest.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.curso.springboot.backend.apirest.models.entity.Usuario;
//UserDetailsService interface propia de Spring Security para trabajar el proceso de login
@Service
public class UsuarioService implements UserDetailsService{

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			logger.error("Error al encontrar un usuario con ese nombre de usuario");
			throw new UsernameNotFoundException("Error al encontrar un usuario con ese nombre de usuario");
		}
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority->logger.info("Role"+authority.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(),
				usuario.getPassword(),
				usuario.getEnabled(),
				true, true, true, authorities);
	}

}
