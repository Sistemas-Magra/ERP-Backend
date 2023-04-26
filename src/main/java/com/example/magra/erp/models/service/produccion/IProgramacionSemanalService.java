package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanal;

public interface IProgramacionSemanalService {
	ProgramacionSemanal save(ProgramacionSemanal progSem);
	List<Map<String, Object>> getListadoProgramaciones(Integer anio, Integer mes);
	ProgramacionSemanal getById(Integer id);
	List<Map<String, Object>> getListadoVersiones(Integer progVigId);
}