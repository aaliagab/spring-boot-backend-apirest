package com.curso.springboot.backend.apirest.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);
	private final static String DIRECTORIO_UPLOAD = "uploads";

	@Override
	public Resource cargar(String nombreFoto) throws MalformedURLException {
		Path ruta = path(nombreFoto);
		log.info(ruta.toString());
		Resource recurso = null;
		recurso = new UrlResource(ruta.toUri());
		if(!recurso.exists() || !recurso.isReadable()) {
			ruta = Paths.get("src/main/resources/static/images").resolve("notuser.png").toAbsolutePath();
			recurso = new UrlResource(ruta.toUri());
			log.error("No se pudo cargar la imagen "+nombreFoto);
		}
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo, Long id) throws IOException {
		String nombreArchivo = id+"_"+archivo.getOriginalFilename().replace(" ", "");
		Path ruta = path(nombreArchivo);
		log.info(ruta.toString());
		Files.copy(archivo.getInputStream(), ruta);
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if(nombreFoto!=null && !nombreFoto.equals("")) {
			Path ruta_delete = path(nombreFoto);
			File archivoAnterior = ruta_delete.toFile();
			if(archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
				return true;
			}
		}
		return false;
	}

	@Override
	public Path path(String nombreFoto) {
		// TODO Auto-generated method stub
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
