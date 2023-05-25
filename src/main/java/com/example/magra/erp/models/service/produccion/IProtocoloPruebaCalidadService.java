package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;

public interface IProtocoloPruebaCalidadService {
	ProtocoloPruebaCalidad save(ProtocoloPruebaCalidad protocolo);
	List<Map<String, Object>> getListado(String razonSocial, String ordenTrabajo, String producto, String fecha);
	ProtocoloPruebaCalidad getProtocoloById(Integer id);
}