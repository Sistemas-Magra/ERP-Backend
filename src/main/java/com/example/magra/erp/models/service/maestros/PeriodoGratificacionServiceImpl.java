package com.example.magra.erp.models.service.maestros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IPeriodoGratificacionDao;
import com.example.magra.erp.models.entity.maestro.PeriodoGratificacion;

@Service
public class PeriodoGratificacionServiceImpl implements IPeriodoGratificacionService {
	
	@Autowired
	private IPeriodoGratificacionDao gratiDao;

	@Override
	public PeriodoGratificacion getPeriodoActivo() {
		return gratiDao.getPeriodoActivo();
	}

}
