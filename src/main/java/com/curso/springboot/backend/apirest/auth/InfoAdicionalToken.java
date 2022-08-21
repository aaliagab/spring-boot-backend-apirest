package com.curso.springboot.backend.apirest.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.curso.springboot.backend.apirest.models.entity.Usuario;
import com.curso.springboot.backend.apirest.models.service.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer{

	@Autowired
	private IUsuarioService usuarioService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		Map<String, Object> info = new HashMap<>();
		Usuario user =  usuarioService.findByUsername(authentication.getName());
		info.put("informacion_adicional", "Hola que tal! ".concat(authentication.getName()));
		info.put("nombre_usuario", user.getId()+" - "+user.getUsername());
		info.put("nombre completo", user.getNombre()+" "+user.getApellido());
		info.put("email", user.getEmail());
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
