package com.example.magra.erp.models.service.seguridad;

import java.util.List;

import com.example.magra.erp.models.entity.seguridad.Modulo;

public interface IModuloService {

	public List<Modulo> findAll();
	
	public List<Modulo> findModulosByUsername(String username);

}