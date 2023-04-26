package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanalVigencia;

public interface IProgramacionSemanalVigenciaService {
	ProgramacionSemanalVigencia save(ProgramacionSemanalVigencia programacionSemanalVigencia);
	ProgramacionSemanalVigencia getById(Integer id);
	List<Map<String, Object>> getMateriales(Integer id, Integer plantaId);
}
