package com.example.magra.erp.models.service.talento_humano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.talento_humano.IAsistenciaDao;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService {
	
	@Autowired
	private IAsistenciaDao asistenciaDao;

	@Override
	public void registrarAsistencias(String json) {
		asistenciaDao.registrarAsistencias(json);
	}

}