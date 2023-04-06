package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.CaracteristicaReporte;

public interface ICaracteristicaReporteService {
	
	List<CaracteristicaReporte> getAllCaracterísticas();
	CaracteristicaReporte save(CaracteristicaReporte caracteristica);
}
