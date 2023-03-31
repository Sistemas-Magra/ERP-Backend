package com.example.magra.erp.models.service.talento_humano;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.talento_humano.Permiso;

public interface IPermisoService {
	Permiso save(Permiso permiso);
	List<Map<String, Object>> getPermisosEmpleado(Integer empleadoId);
}
