package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.Planta;

public interface IPlantaService {
	List<Planta> getPlantasActivas();
	Planta getPlantaById(Integer id);
}