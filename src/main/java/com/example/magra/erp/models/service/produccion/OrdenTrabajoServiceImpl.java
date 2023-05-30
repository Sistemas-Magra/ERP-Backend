package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IOrdenTrabajoDao;
import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;

@Service
public class OrdenTrabajoServiceImpl implements IOrdenTrabajoService{
	@Autowired
	private IOrdenTrabajoDao otDao;

	@Override
	public OrdenTrabajo save(OrdenTrabajo ot) {
		return otDao.save(ot);
	}

	@Override
	public Integer getCatOT() {
		return otDao.getCatOT();
	}

	@Override
	public List<OrdenTrabajo> autocomplete(String term) {
		return otDao.autocomplete(term);
	}

	@Override
	public List<Map<String, Object>> getListadoOrdenesTrabajo() {
		return otDao.getListadoOrdenesTrabajo();
	}

	@Override
	public List<Map<String, Object>> getDetalleOrdenTrabajo(Integer ordenTrabajoId) {
		return otDao.getDetalleOrdenTrabajo(ordenTrabajoId);
	}

	@Override
	public List<Map<String, Object>> getProductosFromOrdenTrabajo(Integer id) {
		return otDao.getProductosFromOrdenTrabajo(id);
	}

	@Override
	public OrdenTrabajo getById(Integer id) {
		return otDao.getById(id);
	}
	
}