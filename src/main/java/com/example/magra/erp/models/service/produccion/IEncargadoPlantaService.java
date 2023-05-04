package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

public interface IEncargadoPlantaService {
	List<Map<String, Object>> getEncargadosAsignados();
	List<Map<String, Object>> getEncargadosAsignadosPorPlanta(Integer plantaId);
	void insertEncargado(Integer idUsuario, Integer plantaId);
}
