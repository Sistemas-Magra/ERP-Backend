package com.example.magra.erp.models.dao.despacho;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.despacho.Formulario;

public interface IFormularioDao extends JpaRepository<Formulario, Integer> {
	@Query(value="EXEC web_t001_sel_listado_formularios ?1 , ?2 , ?3", nativeQuery=true)
	List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido);
	
	@Query("SELECT f FROM Formulario f WHERE f.id = ?1")
	Formulario getById(Integer id);
}