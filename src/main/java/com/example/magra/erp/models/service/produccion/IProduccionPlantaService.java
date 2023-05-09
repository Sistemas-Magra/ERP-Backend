package com.example.magra.erp.models.service.produccion;

import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;

public interface IProduccionPlantaService {
	ProduccionPlanta save(ProduccionPlanta produccionPlanta);
	Integer getIdByProduccionAndPlanta(Integer plantaId, Integer prodId);
	ProduccionPlanta getById(Integer id);
}