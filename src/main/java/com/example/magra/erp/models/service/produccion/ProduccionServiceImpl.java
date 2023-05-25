package com.example.magra.erp.models.service.produccion;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.helper.ProduccionExcelProtocoloPrueba;
import com.example.magra.erp.helper.ProduccionWordActaConformidad;
import com.example.magra.erp.helper.ProduccionWordCartaGarantia;
import com.example.magra.erp.helper.ProduccionWordControlCalidad;
import com.example.magra.erp.models.dao.produccion.IOrdenTrabajoDao;
import com.example.magra.erp.models.dao.produccion.IProduccionDao;
import com.example.magra.erp.models.dao.produccion.IProtocoloPruebaCalidadDao;
import com.example.magra.erp.models.entity.produccion.Produccion;
import com.example.magra.erp.models.entity.produccion.ProtocoloPruebaCalidad;

@Service
public class ProduccionServiceImpl implements IProduccionService {
	@Autowired
	private IProduccionDao produccionDao;
	
	@Autowired
	private IOrdenTrabajoDao otService;
	
	@Autowired
	private IProtocoloPruebaCalidadDao protocoloDao;

	@Override
	public Integer getIdByFecha() {
		return produccionDao.getIdByFecha();
	}

	@Override
	public Produccion getById(Integer id) {
		return produccionDao.getById(id);
	}

	@Override
	public Produccion save(Produccion produccion) {
		return produccionDao.save(produccion);
	}

	@Override
	public List<Map<String, Object>> getListadoProduccion(String fechaDesde, String fechaHasta, Integer estadoId) {
		return produccionDao.getListadoProduccion(fechaDesde, fechaHasta, estadoId);
	}

	@Override
	public List<Map<String, Object>> getListadoOrdenesVentaByMes(Integer idMes) {
		return produccionDao.getListadoOrdenesVentaByMes(idMes);
	}

	@Override
	public List<Map<String, Object>> getListadoDetalleOrdenesVentaByMes(Integer idMes) {
		return produccionDao.getListadoDetalleOrdenesVentaByMes(idMes);
	}

	@Override
	public List<Map<String, Object>> getStockMensual(Integer idMes) {
		return produccionDao.getStockMensual(idMes);
	}

	@Override
	public void generarActaConformidad(Integer ordenTrabajoId, Integer sedeId, String fechaInicio, String fechaFin, List<Map<String, Object>> detalle) throws IOException {
		Map<String, Object> datos = otService.getDatosCartaActaConformidad(ordenTrabajoId, sedeId);
		Map<String, Object> datosAux = new HashMap<>();
		
		for(String k: datos.keySet()) {
			datosAux.put(k, datos.get(k));
		}
		
		String fechaInicioAux = "";
		String fechaFinAux = "";
		
		fechaInicioAux = fechaInicioAux + fechaInicio.split("pryx")[1];		
		fechaFinAux = fechaFinAux + fechaFin.split("pryx")[1];
		
		String fechaIAux = fechaInicio.split("pryx")[0];
		String fechaFAux = fechaFin.split("pryx")[0];
		
		fechaInicioAux = fechaInicioAux + " con fecha "  + fechaIAux.substring(fechaIAux.length() - 2, fechaIAux.length()) + " DE ";
		fechaInicioAux = fechaInicioAux + fechaIAux.substring(4, fechaIAux.length() - 2) + " DEL ";
		fechaInicioAux = fechaInicioAux + fechaIAux.substring(0, 4);
		
		fechaFinAux = fechaFinAux + " con fecha "  + fechaFAux.substring(fechaFAux.length() - 2, fechaFAux.length()) + " DE ";
		fechaFinAux = fechaFinAux + fechaFAux.substring(4, fechaFAux.length() - 2) + " DEL ";
		fechaFinAux = fechaFinAux + fechaFAux.substring(0, 4);
		
		datosAux.put("fechaInicio", fechaInicioAux);
		datosAux.put("fechaFin", fechaFinAux);
		
		ProduccionWordActaConformidad.generarActaConformidad(datosAux, detalle);
	}

	@Override
	public void generarCartaGarantia(Integer ordenTrabajoId, Integer sedeId, Integer tipoProductoId) throws IOException, InvalidFormatException {
		Map<String, Object> datos = otService.getDatosCartaGarantia(ordenTrabajoId, sedeId);
		List<Map<String, Object>> productos = otService.getDatosCartaGarantiaProductos(ordenTrabajoId, tipoProductoId);
		ProduccionWordCartaGarantia.generarCartaGarantia(datos, productos, tipoProductoId);
	}

	@Override
	public void generarCartaCalidad(Integer ordenTrabajoId, Integer sedeId, String fecha, List<Map<String, Object>> detalle) throws IOException, InvalidFormatException {
		Map<String, Object> datos = otService.getDatosCartaControlCalidad(ordenTrabajoId, sedeId);
		Map<String, Object> datosAux = new HashMap<>();
		
		for(String k: datos.keySet()) {
			datosAux.put(k, datos.get(k));
		}
		
		String fechaCita = "";
		
		fechaCita = fechaCita + fecha.split("pryx")[0];
		String fechaAux = fecha.split("pryx")[1];
		
		fechaCita = fechaCita + " "  + fechaAux.substring(fechaAux.length() - 2, fechaAux.length()) + " DE ";
		fechaCita = fechaCita + fechaAux.substring(4, fechaAux.length() - 2);

		fechaCita = fechaCita + " del presente a las " + fecha.split("pryx")[2];
		
		datosAux.put("fechaCita", fechaCita);
		
		ProduccionWordControlCalidad.generarControlCalidad(datosAux, detalle);
	}

	@Override
	public void generarProtocolo(Integer ordenVentaId) throws IOException, InvalidFormatException {
		List<ProtocoloPruebaCalidad> protocolos = protocoloDao.getLitByVenta(ordenVentaId);
		ProduccionExcelProtocoloPrueba.generarProtocolo(protocolos);
	}
	
}