package com.example.magra.erp.models.service.transporte;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;

public interface IEmpresaTransporteService {
	List<EmpresaTransporte> getAll();
	EmpresaTransporte save(EmpresaTransporte empTrans);
}