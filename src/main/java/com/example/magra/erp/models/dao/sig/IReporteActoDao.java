package com.example.magra.erp.models.dao.sig;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.sig.ReporteActosCondiciones;

public interface IReporteActoDao extends JpaRepository<ReporteActosCondiciones, Integer> {
	@Query(value="EXEC web_m003_sel_listado_reportes ?1, ?2, ?3", nativeQuery=true)
	List<Map<String, Object>> getlistadoReporte(String fecha, Integer plantaId, Integer estadoId);
}