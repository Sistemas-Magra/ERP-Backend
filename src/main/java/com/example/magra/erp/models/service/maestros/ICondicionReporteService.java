package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.CondicionReporte;

public interface ICondicionReporteService {
	
	List<CondicionReporte> getAllCondiciones();
	CondicionReporte save(CondicionReporte condicion);
}
