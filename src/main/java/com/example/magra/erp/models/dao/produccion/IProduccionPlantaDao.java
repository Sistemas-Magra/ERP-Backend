package com.example.magra.erp.models.dao.produccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.ProduccionPlanta;

public interface IProduccionPlantaDao extends JpaRepository<ProduccionPlanta, Integer> {
	@Query(value="SELECT id FROM prod_produccion_planta WHERE planta_id = ?1 AND produccion_id = ?2", nativeQuery=true)
	Integer getIdByProduccionAndPlanta(Integer plantaId, Integer prodId);
	
	@Query("SELECT pp FROM ProduccionPlanta pp WHERE pp.id = ?1")
	ProduccionPlanta getById(Integer id);
	
	@Query(value="EXEC web_p003_man_actualizar_stock_mensual ?1", nativeQuery=true)
	void actualizarProduccion(String stickerProduccion);
	
	@Query(value="UPDATE otd\r\n"
			+ "SET otd.cantidad_aceptada = aprob.cantidad_aprobada,\r\n"
			+ "	otd.cantidad_rechazada = rech.cantidad_rechazada\r\n"
			+ "FROM prod_orden_trabajo_detalle otd\r\n"
			+ "CROSS APPLY (\r\n"
			+ "	SELECT COUNT(ppp.id) AS cantidad_aprobada\r\n"
			+ "	FROM prod_produccion_planta_poste ppp\r\n"
			+ "	WHERE ppp.orden_trabajo_detalle_id = otd.id AND ppp.ind_conformidad = 1\r\n"
			+ ") AS aprob\r\n"
			+ "CROSS APPLY (\r\n"
			+ "	SELECT COUNT(ppp.id) AS cantidad_rechazada\r\n"
			+ "	FROM prod_produccion_planta_poste ppp\r\n"
			+ "	WHERE ppp.orden_trabajo_detalle_id = otd.id AND ppp.ind_conformidad = 0\r\n"
			+ ") AS rech;"
			+ " SELECT 1", nativeQuery=true)
	void actualizarCalidad();
}