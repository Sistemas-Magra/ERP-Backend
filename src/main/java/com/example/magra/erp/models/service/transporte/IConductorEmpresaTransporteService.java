package com.example.magra.erp.models.service.transporte;

import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;

public interface IConductorEmpresaTransporteService {
	ConductorEmpTrans save(ConductorEmpTrans conductor);
	ConductorEmpTrans getByConductor(String licencia);
}