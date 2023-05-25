package com.example.magra.erp.models.service.produccion;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.example.magra.erp.models.entity.produccion.Produccion;

public interface IProduccionService {
	Integer getIdByFecha();
	Produccion getById(Integer id);
	Produccion save(Produccion produccion);
	List<Map<String, Object>> getListadoProduccion(String fechaDesde, String fechaHasta, Integer estadoId);
	List<Map<String, Object>> getListadoOrdenesVentaByMes(Integer idMes);
	List<Map<String, Object>> getListadoDetalleOrdenesVentaByMes(Integer idMes);
	List<Map<String, Object>> getStockMensual(Integer idMes);
	void generarActaConformidad(Integer ordenTrabajoId, Integer sedeId, String fechaInicio, String fechaFin, List<Map<String, Object>> detalle) throws IOException;
	void generarCartaGarantia(Integer ordenTrabajoId, Integer sedeId, Integer tipoProductoId) throws IOException, InvalidFormatException;
	void generarCartaCalidad(Integer ordenTrabajoId, Integer sedeId, String fecha, List<Map<String, Object>> detalle) throws IOException, InvalidFormatException;
	void generarProtocolo(Integer ordenVentaId) throws IOException, InvalidFormatException;
}