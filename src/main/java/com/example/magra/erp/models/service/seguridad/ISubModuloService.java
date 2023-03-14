package com.example.magra.erp.models.service.seguridad;

import java.util.List;

import com.example.magra.erp.models.entity.seguridad.SubModulo;

public interface ISubModuloService {

	public List<SubModulo> findAll();
	
	public List<SubModulo> findAllByIdModulo(Integer id);

}
