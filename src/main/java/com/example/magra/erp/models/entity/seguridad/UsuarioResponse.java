package com.example.magra.erp.models.entity.seguridad;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="usuarioResponse", types= {Usuario.class})
public interface UsuarioResponse {
	Integer getId();
	String getNombres();
	String getApellidoPaterno();
	String getApellidoMaterno();
	String getUsername();
	String getEmail();
	String getCelular();
	
	String getNroDocumento();
	Date getFechaNacimiento();
	String getFoto();
	List<Role> getRoles();
	Boolean getEnabled();
}
