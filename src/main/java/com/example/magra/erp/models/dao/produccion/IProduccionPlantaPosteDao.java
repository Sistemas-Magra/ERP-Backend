package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.ProduccionPlantaPoste;

public interface IProduccionPlantaPosteDao extends JpaRepository<ProduccionPlantaPoste, Integer> {
	@Query(value="EXEC web_d002_sel_autocomplete_postes ?1 , ?2", nativeQuery=true)
	List<Map<String, Object>> postesAutocomplete(String term, Integer ordenVentaId);

	@Query(value="SELECT DISTINCT\r\n"
			+ " 6 AS plantaId,\r\n"
			+ "	pv.id AS productoId,\r\n"
			+ "	pv.nombre AS producto\r\n"
			+ "FROM prod_produccion_planta_poste ppp\r\n"
			+ "JOIN prod_orden_trabajo_detalle otd ON otd.id = ppp.orden_trabajo_detalle_id\r\n"
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.id = otd.orden_venta_detalle_id\r\n"
			+ "JOIN ven_orden_venta ov ON ov.id = ovd.orden_venta_id\r\n"
			+ "JOIN mae_producto_venta pv ON pv.id = ovd.producto_id\r\n"
			+ "WHERE pv.nombre LIKE CONCAT('%', ?1 , '%')\r\n"
			+ "AND pv.tipo_producto_id = 2", nativeQuery=true)
	List<Map<String, Object>> accesoriosAutocomplete(String term);
}