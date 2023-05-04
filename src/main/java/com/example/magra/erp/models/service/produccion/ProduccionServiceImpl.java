package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProduccionDao;
import com.example.magra.erp.models.entity.produccion.Produccion;

@Service
public class ProduccionServiceImpl implements IProduccionService {
	@Autowired
	private IProduccionDao produccionDao;

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
	
}