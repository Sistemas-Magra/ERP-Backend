package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.ProgramacionSemanal;

public interface IProgramacionSemanalDao extends JpaRepository<ProgramacionSemanal, Integer> {
	@Query(value="EXEC p001_web_sel_listado_programacion_semanal_produccion ?1 , ?2", nativeQuery=true)
	List<Map<String, Object>> getListadoProgramaciones(Integer anio, Integer mes);
	
	@Query("SELECT ps FROM ProgramacionSemanal ps WHERE ps.id = ?1")
	ProgramacionSemanal getById(Integer id);
	
	@Query(value=" 	SELECT "
			+ "			sem.id, "
			+ "			UPPER(FORMAT(sem.fecha_crea, 'dd MMM yyyy')) AS fecha_crea, "
			+ " 		FORMAT(sem.fecha_crea, 'HH:mm') AS hora_crea, "
			+ "			sem.version,"
			+ "			usu.username "
			+ "		FROM prod_programacion_semanal sem "
			+ "		JOIN sti_usuario usu ON sem.id_usuario_crea = usu.id "
			+ "		WHERE sem.programacion_semanal_vigencia_id = ?1", nativeQuery=true)
	List<Map<String, Object>> getListadoVersiones(Integer progVigId);
}