package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.Produccion;

public interface IProduccionDao extends JpaRepository<Produccion, Integer> {
	@Query(value="EXEC web_p002_sel_get_listado ?1 , ?2 , ?3", nativeQuery=true)
	List<Map<String, Object>> getListadoProduccion(String fechaDesde, String fechaHasta, Integer estadoId);
	
	@Query(value="SELECT id FROM prod_produccion WHERE CONVERT(DATE, fecha) = CONVERT(DATE, GETDATE())", nativeQuery=true)
	Integer getIdByFecha();
	
	@Query("SELECT p FROM Produccion p WHERE p.id = ?1")
	Produccion getById(Integer id);
	
	@Query(value="EXEC web_p003_sel_listado_inventario_parte_fija ?1", nativeQuery=true)
	List<Map<String, Object>> getListadoOrdenesVentaByMes(Integer idMes);
	
	@Query(value="EXEC web_p003_sel_listado_inventario ?1", nativeQuery=true)
	List<Map<String, Object>> getListadoDetalleOrdenesVentaByMes(Integer idMes);
	
	@Query(value="SELECT * FROM prod_stock_mensual WHERE id_mes = ?1", nativeQuery=true)
	List<Map<String, Object>> getStockMensual(Integer idMes);
	
	@Query(value="SELECT \r\n"
			+ " 'item' AS producto,\r\n"
			+ " 0 AS cantidad,\r\n"
			+ "	'08:00 am' AS hora_inicio,\r\n"
			+ "	'18 de mayo del 2023' AS fecha_inicio,\r\n"
			+ "	'ELECTROCENTRO' AS cliente,\r\n"
			+ "	'Obra de Prueba' AS nombre_obra,\r\n"
			+ "	'06:00 pm' AS hora_fin,\r\n"
			+ "	'18 de mayo del 2023' AS fecha_fin\r\n"
			+ "FROM ven_orden_venta ov", nativeQuery=true)
	List<Map<String, Object>> getDatosActaConformidad();
	
}