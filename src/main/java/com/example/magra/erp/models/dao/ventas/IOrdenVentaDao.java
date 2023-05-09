package com.example.magra.erp.models.dao.ventas;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.ventas.OrdenVenta;

public interface IOrdenVentaDao extends JpaRepository<OrdenVenta, Integer> {
	@Query("SELECT ot.id FROM OrdenTrabajo ot WHERE ot.ordenVenta.id = ?1")
	Integer getOrdenTrabajoIdByOrdenVenta(Integer id);
	
	@Query(value="EXEC web_v001_sel_listado_ventas_cotizaciones ?1 , ?2 , ?3 , ?4", nativeQuery=true)
	List<Map<String, Object>> getListadoMaestro(String cliente, String fechaDesde, String fechaHasta, Integer indVerAnulados);
	
	@Query(value="SELECT COUNT(id) + 1 FROM ven_orden_venta", nativeQuery=true)
	Integer getCodigo();
	
	@Query("SELECT ov FROM OrdenVenta ov WHERE ov.id = ?1")
	OrdenVenta getById(Integer id);
}