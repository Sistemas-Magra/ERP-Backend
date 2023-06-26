package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajoDetalle;

public interface IOrdenTrabajoDetalleDao extends JpaRepository<OrdenTrabajoDetalle, Integer> {
	
	@Query(value="SELECT\r\n"
			+ "	otd.orden_venta_detalle_id,\r\n"
			+ "	ovd.cantidad AS cantidad_solicitada,\r\n"
			+ "	otd.cantidad_producida,\r\n"
			+ "	otd.cantidad_pendiente,\r\n"
			+ "	otd.cantidad_despachada,\r\n"
			+ "	ovd.cantidad - otd.cantidad_despachada AS cantidad_pend_despacho\r\n"
			+ "FROM prod_orden_trabajo_detalle otd\r\n"
			+ "JOIN ven_orden_venta_detalle ovd ON ovd.id = otd.orden_venta_detalle_id\r\n"
			+ "JOIN prod_orden_trabajo ot ON ot.id = otd.orden_trabajo_id\r\n"
			+ "WHERE ot.orden_venta_id = ?1", nativeQuery=true)
	List<Map<String, Object>> getPendientes(Integer ordenVentaId);
	
	@Query(value="UPDATE otd\r\n"
			+ "SET otd.cantidad_producida = otd.cantidad_producida + 1,\r\n"
			+ "	otd.cantidad_pendiente = otd.cantidad_pendiente - 1\r\n"
			+ "FROM prod_orden_trabajo_detalle otd\r\n"
			+ "WHERE otd.id = ?1; SELECT 1; ", nativeQuery=true)
	void updateAvance(Integer otdId);
}
