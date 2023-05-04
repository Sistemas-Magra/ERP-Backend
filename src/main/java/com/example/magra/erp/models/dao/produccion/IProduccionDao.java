package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.Produccion;

public interface IProduccionDao extends JpaRepository<Produccion, Integer> {
	@Query(value="EXEC web_p002_sel_get_listado ?1 , ?2 , ?3", nativeQuery=true)
	List<Map<String, Object>> getListadoProduccion(String fechaDesde, String fechaHasta, Integer estadoId);
	
	@Query(value="SELECT id FROM prod_produccion WHERE CONVERT(DATE, fecha) = CONVERT(DATE, GETDATE())", nativeQuery=true)
	Integer getIdByFecha();
	
	@Query("SELECT p FROM Produccion p WHERE p.id = ?1")
	Produccion getById(Integer id);
}