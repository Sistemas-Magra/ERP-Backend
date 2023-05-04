package com.example.magra.erp.models.dao.produccion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;

public interface IProduccionPlantaDao extends JpaRepository<ProduccionPlanta, Integer> {
	
}