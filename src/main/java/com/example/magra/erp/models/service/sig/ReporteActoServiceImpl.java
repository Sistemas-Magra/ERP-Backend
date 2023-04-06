package com.example.magra.erp.models.service.sig;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.sig.IReporteActoDao;
import com.example.magra.erp.models.entity.sig.ReporteActosCondiciones;

@Service
public class ReporteActoServiceImpl implements IReporteActoService {
	@Autowired
	private IReporteActoDao reporteDao;

	@Override
	public ReporteActosCondiciones save(ReporteActosCondiciones reporte) {
		return reporteDao.save(reporte);
	}

	@Override
	public List<Map<String, Object>> getlistadoReporte(String fecha, Integer plantaId, Integer estadoId) {
		return reporteDao.getlistadoReporte(fecha, plantaId, estadoId);
	}

}
