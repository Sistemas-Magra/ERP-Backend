package com.example.magra.erp.models.service.transporte;

import java.util.Map;

import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;

public interface IConductorEmpresaTransporteService {
	ConductorEmpTrans save(ConductorEmpTrans conductor);
	ConductorEmpTrans getByConductor(String licencia);
	Map<String, Object> getDatosFromSunat(String nroDocumento);
	Integer getIdByNroDocumentoAndEmpresaId(Integer empresaTransporteId, String nroDocumento);
}