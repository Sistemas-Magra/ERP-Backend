package com.example.magra.erp.models.dao.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;

public interface IProtocoloPruebaCalidadDao extends JpaRepository<ProtocoloPruebaCalidad, Integer> {
	@Query("SELECT ppc FROM ProtocoloPruebaCalidad ppc WHERE ppc.ordenVenta.id = ?1")
	List<ProtocoloPruebaCalidad> getLitByVenta(Integer ordenVentaId);
	
	@Query("SELECT p FROM ProtocoloPruebaCalidad p WHERE p.id = ?1")
	ProtocoloPruebaCalidad getProtocoloById(Integer id);
	
	@Query(value="SELECT TOP(200)\r\n"
			+ "	prot.id,\r\n"
			+ "	ot.codigo AS orden_trabajo,\r\n"
			+ "	ov.codigo AS orden_venta,\r\n"
			+ "	UPPER(FORMAT(prot.fecha_inicio, 'dd MMM yyyy')) AS fecha,\r\n"
			+ "	cli.razon_social AS cliente,\r\n"
			+ "	pv.nombre AS producto,\r\n"
			+ "	pro_ct.cantidad AS cant_carg_trab,\r\n"
			+ "	pro_rot.cantidad AS cant_rot\r\n"
			+ "FROM prod_protocolo_prueba prot\r\n"
			+ "JOIN ven_orden_venta ov ON ov.id = prot.orden_venta_id\r\n"
			+ "JOIN mae_cliente cli ON cli.id = ov.cliente_id\r\n"
			+ "JOIN mae_producto_venta pv ON prot.producto_id = pv.id\r\n"
			+ "JOIN prod_orden_trabajo ot ON ot.orden_venta_id = ov.id\r\n\r\n"
			+ "CROSS APPLY (\r\n"
			+ "	SELECT COUNT(pct.id) AS cantidad FROM prod_protocolo_prueba_carga_trabajo pct WHERE pct.protocolo_prueba_id = prot.id\r\n"
			+ ") AS pro_ct\r\n"
			+ "CROSS APPLY (\r\n"
			+ "	SELECT COUNT(rot.id) AS cantidad FROM prod_protocolo_prueba_rotura rot WHERE rot.protocolo_prueba_id = prot.id\r\n"
			+ ") AS pro_rot\r\n"
			+ "WHERE	((UPPER(cli.razon_social) LIKE UPPER(CONCAT('%', ?1 , '%'))) OR (1=1))\r\n"
			+ "AND		((UPPER(ot.codigo) LIKE UPPER(CONCAT('%', ?2 , '%'))) OR (1=1))\r\n"
			+ "AND		((UPPER(pv.nombre) LIKE UPPER(CONCAT('%', ?3 , '%'))) OR (1=1))\r\n"
			+ "AND		((CONVERT(DATE, prot.fecha_inicio) = CONVERT(DATE, ?4 )) OR (1=1))\r\n", nativeQuery=true)
	List<Map<String, Object>> getListado(String razonSocial, String ordenTrabajo, String producto, String fecha);
	
	@Query(value="SELECT DISTINCT porc.abreviatura\r\n"
			+ "FROM prod_protocolo_prueba ppp\r\n"
			+ "JOIN prod_protocolo_prueba_carga_trabajo pppct ON pppct.protocolo_prueba_id = ppp.id\r\n"
			+ "JOIN prod_protocolo_prueba_carga_trabajo_muestra pppctm ON pppctm.protocolo_prueba_carga_trabajo_id = pppct.id\r\n"
			+ "JOIN cfg_tabla_auxiliar_detalle porc ON porc.id = pppctm.porcentaje_id AND porc.cod_tabla_auxiliar = pppctm.porcentaje_cod_tabla_auxiliar\r\n"
			+ "WHERE ppp.id = ?1", nativeQuery=true)
	List<String> getListAbreviaturasCargaTrabajo(Integer id);
	
	@Query(value="SELECT DISTINCT porc.abreviatura\r\n"
			+ "FROM prod_protocolo_prueba ppp\r\n"
			+ "JOIN prod_protocolo_prueba_rotura pppcr ON pppcr.protocolo_prueba_id = ppp.id\r\n"
			+ "JOIN prod_protocolo_prueba_rotura_muestra pppcrm ON pppcrm.protocolo_prueba_rotura_id = pppcr.id\r\n"
			+ "JOIN cfg_tabla_auxiliar_detalle porc ON porc.id = pppcrm.porcentaje_id AND porc.cod_tabla_auxiliar = pppcrm.porcentaje_cod_tabla_auxiliar\r\n"
			+ "WHERE ppp.id = ?1", nativeQuery=true)
	List<String> getListAbreviaturasRotura(Integer id);
}