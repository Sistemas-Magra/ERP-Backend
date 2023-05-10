package com.example.magra.erp.models.service.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.seguridad.IUsuarioPlantaDao;
import com.example.magra.erp.models.entity.seguridad.UsuarioPlanta;

@Service
public class UsuarioPlantaServiceImpl implements IUsuarioPlantaService {
	
	@Autowired
	private IUsuarioPlantaDao usuarioPlantaDao;

	@Override
	public UsuarioPlanta getByUsuarioId(Integer usuarioId) {
		return usuarioPlantaDao.getByUsuarioId(usuarioId);
	}

}
