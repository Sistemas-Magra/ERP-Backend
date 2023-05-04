package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.EncargadoPlanta;

public interface IEncargadoPlantaDao extends JpaRepository<EncargadoPlanta, Integer> {
	@Query(value="SELECT id FROM mae_encargado_planta WHERE CONVERT(DATE, fecha) = CONVERT(DATE, GETDATE())", nativeQuery=true)
	List<Map<String, Object>> getEncargadosAsignados();

	@Query(value="	SELECT usu.id, usu.nombre_completo "
			+ "		FROM mae_encargado_planta ep "
			+ "		JOIN sti_usuario usu ON usu.id = ep.id_usuario_encargado"
			+ "		WHERE ep.planta_id = ?1 "
			+ "		AND CONVERT(DATE, ep.fecha) = CONVERT(DATE, GETDATE())", nativeQuery=true)
	List<Map<String, Object>> getEncargadosAsignadosPorPlanta(Integer plantaId);

	@Query(value="INSERT INTO mae_encargado_planta(id_usuario_encargado, planta_id, fecha) VALUES ( ?1, ?2 , GETDATE()); SELECT 1", nativeQuery=true)
	void insertEncargado(Integer idUsuario, Integer plantaId);
	
}