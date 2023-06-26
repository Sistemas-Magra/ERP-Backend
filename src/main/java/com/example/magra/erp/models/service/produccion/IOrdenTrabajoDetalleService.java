package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

public interface IOrdenTrabajoDetalleService {
	void updateAvance(Integer otdId);
	List<Map<String, Object>> getPendientes(Integer ordenVentaId);
}