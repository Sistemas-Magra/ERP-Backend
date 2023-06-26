package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;

public interface IOrdenTrabajoDao extends JpaRepository<OrdenTrabajo, Integer>{
	
	@Query("SELECT ot FROM OrdenTrabajo ot WHERE ot.id = ?1")
	OrdenTrabajo getById(Integer id);
	
	@Query("SELECT COUNT(ot) FROM OrdenTrabajo ot")
	Integer getCatOT();
	
	@Query("SELECT ot "
			+ " FROM OrdenTrabajo ot "
			+ " WHERE (UPPER(ot.ordenVenta.codigo) LIKE UPPER(CONCAT('%', ?1 , '%'))) "
			+ " AND ot.estado.tablaAuxiliarDetalleId.id IN (1, 2)")
	List<OrdenTrabajo> autocompletePedido(String term);
	
	@Query("SELECT ot "
			+ " FROM OrdenTrabajo ot "
			+ " WHERE (UPPER(ot.codigo) LIKE UPPER(CONCAT('%', ?1 , '%'))) "
			+ " AND ot.estado.tablaAuxiliarDetalleId.id IN (1, 2)")
	List<OrdenTrabajo> autocomplete(String term);
	
	@Query(value="SELECT CAST(0 AS BIT) AS indUso, pv.nombre AS producto, NULL AS cantidad, otd.cantidad_pendiente\r\n"
			+ "FROM prod_orden_trabajo ot\r\n"
			+ "JOIN ven_orden_venta ov ON ov.id = ot.orden_venta_id\r\n"
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.orden_venta_id = ov.id\r\n"
			+ "JOIN prod_orden_trabajo_detalle otd ON otd.orden_trabajo_id = ot.id AND ovd.id = otd.orden_venta_detalle_id\r\n"
			+ "JOIN mae_producto_venta pv ON pv.id = ovd.producto_id "
			+ "WHERE ot.id = ?1", nativeQuery=true)
	List<Map<String, Object>> getProductosFromOrdenTrabajo(Integer id);
	
	@Query(value="SELECT "
			+ "	ot.id AS orden_trabajo_id, "
			+ "	ov.id AS orden_venta_id, "
			+ "	ov.codigo AS pedido, "
			+ "	ot.codigo AS ord_trab, "
			+ "	cli.razon_social AS cliente, "
			+ "	COUNT(ovd.id) AS cant_productos, "
			+ "	100.00*SUM(ISNULL(ppp.cantidad, 0))/SUM(ISNULL(ovd.cantidad, 0)) AS porc_avance, "
			+ "	est.nombre AS estado "
			+ "FROM ven_orden_venta ov "
			+ "JOIN mae_cliente cli ON cli.id = ov.cliente_id "
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.orden_venta_id = ov.id "
			+ "JOIN prod_orden_trabajo ot ON ot.orden_venta_id = ov.id "
			+ "JOIN prod_orden_trabajo_detalle otd ON otd.orden_trabajo_id = ot.id AND otd.orden_venta_detalle_id = ovd.id "
			+ "LEFT JOIN prod_produccion_planta_poste ppp ON ppp.orden_trabajo_detalle_id = otd.id AND ppp.orden_trabajo_id = ot.id "
			+ "JOIN cfg_tabla_auxiliar_detalle est ON est.cod_tabla_auxiliar = ot.estado_cod_tabla_auxiliar AND est.id = ot.estado_id "
			+ "GROUP BY ot.id, ov.id, ov.codigo, ot.codigo, cli.razon_social, est.nombre", nativeQuery=true)
	List<Map<String, Object>> getListadoOrdenesTrabajo();
	
	@Query(value="SELECT "
			+ "	pv.nombre AS producto, "
			+ " ovd.cantidad AS cant_solicitada, "
			+ "	otd.cantidad_producida, "
			+ "	otd.cantidad_pendiente, "
			+ "	otd.cantidad_despachada, "
			+ "	ovd.cantidad - otd.cantidad_despachada AS cantidad_pendiente_despacho, "
			+ "	otd.cantidad_aceptada, "
			+ "	otd.cantidad_rechazada, "
			+ "	100.00*(otd.cantidad_producida/ovd.cantidad) AS porc_producido, "
			+ "	100.00*(otd.cantidad_pendiente/ovd.cantidad) AS porc_pendiente, "
			+ "	100.00*(otd.cantidad_despachada/ovd.cantidad) AS porc_despachado, "
			+ "	100.00*(ovd.cantidad - otd.cantidad_despachada)/ovd.cantidad AS porc_pendiente_despacho, "
			+ "	100.00*(otd.cantidad_aceptada/ovd.cantidad) AS porc_aceptacion, "
			+ "	100.00*(otd.cantidad_rechazada/ovd.cantidad) AS porc_rechazo "
			+ "FROM prod_orden_trabajo_detalle otd "
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.id = otd.orden_venta_detalle_id "
			+ "JOIN mae_producto_venta pv ON pv.id = ovd.producto_id "
			+ "WHERE otd.orden_trabajo_id = ?1", nativeQuery=true)
	List<Map<String, Object>> getDetalleOrdenTrabajo(Integer ordenTrabajoId);	
	
	@Query(value="EXEC web_p004_sel_datos_carta_control_calidad ?1 , ?2", nativeQuery=true)
	Map<String, Object> getDatosCartaControlCalidad(Integer ordenTrabajoId, Integer sedeId);
	
	@Query(value="EXEC web_p004_sel_datos_carta_acta_conformidad ?1 , ?2", nativeQuery=true)
	Map<String, Object> getDatosCartaActaConformidad(Integer ordenTrabajoId, Integer sedeId);
	
	@Query(value="EXEC web_p004_sel_datos_carta_garantia ?1 , ?2", nativeQuery=true)
	Map<String, Object> getDatosCartaGarantia(Integer ordenTrabajoId, Integer sedeId);
	
	@Query(value="SELECT pv.nombre AS producto, ovd.cantidad AS cantidad\r\n"
			+ "FROM prod_orden_trabajo ot\r\n"
			+ "JOIN ven_orden_venta ov ON ot.orden_venta_id = ov.id\r\n"
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.orden_venta_id = ov.id\r\n"
			+ "JOIN mae_producto_venta pv ON pv.id = ovd.producto_id\r\n"
			+ "WHERE ot.id = ?1 AND pv.tipo_producto_id = ?2", nativeQuery=true)
	List<Map<String, Object>> getDatosCartaGarantiaProductos(Integer ordenTrabajoId, Integer tipoProductoId);
}