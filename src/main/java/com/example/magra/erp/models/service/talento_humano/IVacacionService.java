package com.example.magra.erp.models.service.talento_humano;

import java.util.List;
import java.util.Map;

public interface IVacacionService {
	List<Map<String, Object>> getVacacionesEmpleado(Integer empleadoId);
	Integer getVacacionesActivas(Integer empleadoId);
}