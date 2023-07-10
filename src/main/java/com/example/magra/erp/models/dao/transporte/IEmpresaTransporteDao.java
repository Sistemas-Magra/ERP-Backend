package com.example.magra.erp.models.dao.transporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;

public interface IEmpresaTransporteDao extends JpaRepository<EmpresaTransporte, Integer> {
	@Query("SELECT et.id FROM EmpresaTransporte et WHERE et.nroDocumentoIdentidad = ?1")
	Integer getIdByNroDocumento(String nroDocumento);
}