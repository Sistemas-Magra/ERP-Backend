package com.example.magra.erp.models.dao.transporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;

public interface IConductorEmpresaTransporteDao extends JpaRepository<ConductorEmpTrans, Integer> {
	@Query("SELECT c FROM ConductorEmpTrans c WHERE c.licencia = ?1")
	ConductorEmpTrans getByConductor(String licencia);
	
	@Query(value="SELECT id FROM mae_conductor_empresa_transporte WHERE empresa_transporte_id = ?1 AND nro_documento_identidad LIKE CONCAT('%', ?2 , '%')", nativeQuery=true)
	Integer getIdByNroDocumentoAndEmpresaId(Integer empresaTransporteId, String nroDocumento);
}