package com.example.magra.erp.models.dao.transporte;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;

public interface IEmpresaTransporteDao extends JpaRepository<EmpresaTransporte, Integer> {
	
}