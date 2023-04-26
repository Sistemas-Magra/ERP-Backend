package com.example.magra.erp;

public class VariablesGlobales {

	public static final String ANGULAR_CLIENTE = "angularapp";
	public static final String ANGULAR_PASSWORD = "12345";
	public static final int ANGULAR_ACCESS_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	public static final int ANGULAR_REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	
	public static final String ORIGENES_PERMITIDOS = "http://localhost:4200";
	
	public static final String RUTA_FRONTEND = "http://localhost:4200";
	
	public static final int MINUTES_EXPIRE_PASSWORD_RESET_TOKEN = 999999999;

	public final static String IMAGEN_DEFAULT = "not-user.png";
	
	public final static String DIRECTORIO_ARCHIVOS = "C:/Proyecto";
	
	public final static String EMPLEADO_FOTOS = DIRECTORIO_ARCHIVOS + "/empleados/fotos";
	
	public final static String PRODUCCION = DIRECTORIO_ARCHIVOS + "/produccion";
	
	public final static String PLANOS = PRODUCCION + "/planos";
	public final static String ESPECIFICACIONES_TECNICAS = PRODUCCION + "/especificaciones_tecnicas";
	
	public final static String API_SUNAT = "https://api.apis.net.pe/v2/";

}
