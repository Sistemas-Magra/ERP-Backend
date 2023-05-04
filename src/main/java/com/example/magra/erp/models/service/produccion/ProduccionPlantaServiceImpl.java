package com.example.magra.erp.models.service.produccion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProduccionPlantaDao;
import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;

@Service
public class ProduccionPlantaServiceImpl implements IProduccionPlantaService {
	@Autowired
	private IProduccionPlantaDao produccionPlantaDao;

	@Override
	public ProduccionPlanta save(ProduccionPlanta produccionPlanta) {
		return produccionPlantaDao.save(produccionPlanta);
	}
	
}