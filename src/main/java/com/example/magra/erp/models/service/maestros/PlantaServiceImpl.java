package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IPlantaDao;
import com.example.magra.erp.models.entity.maestro.Planta;

@Service
public class PlantaServiceImpl implements IPlantaService {
	
	@Autowired
	private IPlantaDao plantaDao;

	@Override
	public List<Planta> getPlantasActivas() {
		return plantaDao.getPlantasActivas();
	}

	@Override
	public Planta getPlantaById(Integer id) {
		return plantaDao.getById(id);
	}

}