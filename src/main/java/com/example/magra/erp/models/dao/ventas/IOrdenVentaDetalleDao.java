package com.example.magra.erp.models.dao.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.ventas.OrdenVentaDetalle;

public interface IOrdenVentaDetalleDao extends JpaRepository<OrdenVentaDetalle, Integer> {
	@Query(value="UPDATE ven_orden_venta_detalle SET plano = ?1 , especificaciones_tecnicas = ?2 WHERE id = ?3 ; SELECT 1", nativeQuery=true)
	void updateFilenames(String plano, String esp, Integer number);
}
