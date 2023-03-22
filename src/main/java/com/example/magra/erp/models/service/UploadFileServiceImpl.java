package com.example.magra.erp.models.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.magra.erp.VariablesGlobales;

@Service
public class UploadFileServiceImpl implements IUploadFileService{

	private final static Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);

	@Override
	public void resizeImage(String nombreFoto, String rutaBase) throws MalformedURLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMarkerToFoto(String nombreFoto, String rutaBase) throws MalformedURLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Path getPath(String nombreFoto, String rutaBase) {
		return Paths.get(rutaBase).resolve(nombreFoto).toAbsolutePath();
	}

	@Override
	public Resource cargar(String nombreFoto, String rutaBase) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto, rutaBase);
		Resource recurso = null;

		recurso = new UrlResource(rutaArchivo.toUri());

		if (!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = getPath(VariablesGlobales.IMAGEN_DEFAULT,VariablesGlobales.DIRECTORIO_ARCHIVOS);
			recurso = new UrlResource(rutaArchivo.toUri());

			logger.error("No se pudo cargar la imagen: " + nombreFoto);
		}
		
		return recurso;
	}

	@Override
	public String copiar(MultipartFile archivo, String rutaBase) throws IOException {
		String filename = archivo.getOriginalFilename().replace(" ", "");
		String nombreArchivoAux =  FilenameUtils.getBaseName(filename) + "_" + UUID.randomUUID().toString() + "." +FilenameUtils.getExtension(filename);
		Path rutaArchivo = getPath(nombreArchivoAux, rutaBase);

		Files.copy(archivo.getInputStream(), rutaArchivo);

		return nombreArchivoAux;
	}

	@Override
	public boolean eliminar(String nombreFoto, String rutaBase) {
		if (nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaArchivoAnterior = getPath(nombreFoto, rutaBase);
			File archivoAnterior = rutaArchivoAnterior.toFile();

			if (archivoAnterior.exists() && archivoAnterior.canRead()) {
				archivoAnterior.delete();
				return true;
			}
		}
		return false;
	}

}