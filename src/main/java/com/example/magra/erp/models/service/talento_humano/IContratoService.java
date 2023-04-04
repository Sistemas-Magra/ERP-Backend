package com.example.magra.erp.models.service.talento_humano;

import java.util.Map;

import com.example.magra.erp.models.entity.talento_humano.Contrato;

public interface IContratoService {
	Contrato save(Contrato contrato);
	Contrato getContratoByEmpleado(Integer empleadoId);
	Map<String, Object> getDatosContrato(Integer empleadoId, Integer mes, Integer anio);
}
