package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IOrdenTrabajoDetalleDao;

@Service
public class OrdenTrabajoDetalleImpl implements IOrdenTrabajoDetalleService {
	@Autowired
	private IOrdenTrabajoDetalleDao ordenTrabajoDetaleDao;

	@Override
	public void updateAvance(Integer otdId) {
		ordenTrabajoDetaleDao.updateAvance(otdId);
	}

	@Override
	public List<Map<String, Object>> getPendientes(Integer ordenVentaId) {
		return ordenTrabajoDetaleDao.getPendientes(ordenVentaId);
	}
}