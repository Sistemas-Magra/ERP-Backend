package com.example.magra.erp.models.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	void resizeImage(String nombreFoto,String rutaBase) throws MalformedURLException;
	void setMarkerToFoto(String nombreFoto,String rutaBase) throws MalformedURLException;
	public Path getPath(String nombreFoto, String rutaBase);
	public Resource cargar(String nombreFoto,String rutaBase) throws MalformedURLException;
	public String copiar(MultipartFile archivo, String rutaBase) throws IOException;
	String copiarConNombre(MultipartFile archivo, String rutaBase, String nombre) throws IOException;
	public boolean eliminar(String nombreFoto, String rutaBase);
	
}