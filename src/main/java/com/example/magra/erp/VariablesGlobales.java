package com.example.magra.erp;

public class VariablesGlobales {

	public static final String ANGULAR_CLIENTE = "angularapp";
	public static final String ANGULAR_PASSWORD = "12345";
	public static final int ANGULAR_ACCESS_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	public static final int ANGULAR_REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	
	public static final String ORIGENES_PERMITIDOS = "http://localhost:4200";
	
	public static final String RUTA_FRONTEND = "http://localhost:4200";
	
	public static final int MINUTES_EXPIRE_PASSWORD_RESET_TOKEN = 10;

	public final static String IMAGEN_DEFAULT = "not-user.png";
	
	public final static String DIRECTORIO_ARCHIVOS = "C:/Proyecto";
	
	public final static String INVENTARIO_TOTAL_IMAGENES = DIRECTORIO_ARCHIVOS + "/inventarios/totales/imagenes";
	
	public final static String INVENTARIO_TOTAL_VIDEOS = DIRECTORIO_ARCHIVOS + "/inventarios/totales/videos";
	
	public final static String INVENTARIO_TOTAL_MUEBLES = DIRECTORIO_ARCHIVOS +  "/inventarios/muebles";
	
	public final static String INVENTARIO_DIARIO_IMAGENES = DIRECTORIO_ARCHIVOS + "/inventarios/ciclicos/imagenes";
	
	public final static String INVENTARIO_DIARIO_VIDEOS = DIRECTORIO_ARCHIVOS + "/inventarios/ciclicos/videos";

	// VARIABLE GLOBAL DE PAGINADO DE MAESTROS
	public final static int ITEMS_PER_PAGE_MAESTROS = 16;

}
