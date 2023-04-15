package com.example.magra.erp.models.service.gestion;

import java.util.List;

import com.example.magra.erp.models.entity.gestion.Empresa;

public interface IEmpresaService {
	List<Empresa> getEmpresasActivas();
}