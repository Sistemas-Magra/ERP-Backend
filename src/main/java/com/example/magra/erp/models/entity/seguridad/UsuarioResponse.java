package com.example.magra.erp.models.entity.seguridad;

import java.util.Date;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.example.magra.erp.models.entity.maestro.Empleado;

@Projection(name="usuarioResponse", types= {Usuario.class})
public interface UsuarioResponse {
	Integer getId();
	String getNombres();
	String getApellidoPaterno();
	String getApellidoMaterno();
	String getUsername();
	String getEmail();
	String getCelular();
	
	Empleado getEmpleado();
	
	String getNroDocumento();
	Date getFechaNacimiento();
	String getFoto();
	List<Role> getRoles();
	Boolean getEnabled();
}
