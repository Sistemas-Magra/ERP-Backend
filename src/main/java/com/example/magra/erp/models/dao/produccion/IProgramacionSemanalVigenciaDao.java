package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanalVigencia;

public interface IProgramacionSemanalVigenciaDao extends JpaRepository<ProgramacionSemanalVigencia, Integer> {
	@Query("SELECT psv FROM ProgramacionSemanalVigencia psv WHERE psv.id = ?1")
	ProgramacionSemanalVigencia getById(Integer id);
	
	@Query(value="EXEC web_p001_sel_get_info_materiales_dia_planta ?1 , ?2", nativeQuery=true)
	List<Map<String, Object>> getMateriales(Integer id, Integer plantaId);
}