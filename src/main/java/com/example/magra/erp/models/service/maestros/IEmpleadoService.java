package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.Empleado;

public interface IEmpleadoService {
	List<Empleado> autocomplete(String term);

}
