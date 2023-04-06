package com.example.magra.erp.models.service.sig;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.sig.ReporteActosCondiciones;

public interface IReporteActoService {
	ReporteActosCondiciones save(ReporteActosCondiciones reporte);
	List<Map<String, Object>> getlistadoReporte(String fecha, Integer plantaId, Integer estadoId);
}