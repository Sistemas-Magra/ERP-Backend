package com.example.magra.erp.models.service.seguridad;

import com.example.magra.erp.models.entity.seguridad.UsuarioPlanta;

public interface IUsuarioPlantaService {
	UsuarioPlanta getByUsuarioId(Integer usuarioId);
}