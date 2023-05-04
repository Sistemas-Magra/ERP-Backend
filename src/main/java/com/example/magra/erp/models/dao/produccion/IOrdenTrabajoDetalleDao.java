package com.example.magra.erp.models.dao.produccion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajoDetalle;

public interface IOrdenTrabajoDetalleDao extends JpaRepository<OrdenTrabajoDetalle, Integer> {
	
	@Query(value="UPDATE otd\r\n"
			+ "SET otd.cantidad_producida = otd.cantidad_producida + 1,\r\n"
			+ "	otd.cantidad_pendiente = otd.cantidad_pendiente - 1\r\n"
			+ "FROM prod_orden_trabajo_detalle otd\r\n"
			+ "WHERE otd.id = ?1; SELECT 1; ", nativeQuery=true)
	void updateAvance(Integer otdId);
}
