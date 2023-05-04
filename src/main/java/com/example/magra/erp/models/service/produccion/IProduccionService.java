package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.produccion.Produccion;

public interface IProduccionService {
	Integer getIdByFecha();
	Produccion getById(Integer id);
	Produccion save(Produccion produccion);
	List<Map<String, Object>> getListadoProduccion(String fechaDesde, String fechaHasta, Integer estadoId);
}