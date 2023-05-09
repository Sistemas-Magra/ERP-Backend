package com.example.magra.erp.models.service.ventas;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.ventas.IOrdenVentaDao;
import com.example.magra.erp.models.entity.ventas.OrdenVenta;

@Service
public class OrdenVentaServiceImpl implements IOrdenVentaService {
	
	@Autowired
	private IOrdenVentaDao ordenVentaDao;

	@Override
	public OrdenVenta save(OrdenVenta ordenVenta) {
		return ordenVentaDao.save(ordenVenta);
	}

	@Override
	public List<Map<String, Object>> getListadoMaestro(String cliente, String fechaDesde, String fechaHasta,
			Integer indVerAnulados) {
		return ordenVentaDao.getListadoMaestro(cliente, fechaDesde, fechaHasta, indVerAnulados);
	}

	@Override
	public Integer getCodigo() {
		return ordenVentaDao.getCodigo();
	}

	@Override
	public OrdenVenta getById(Integer id) {
		return ordenVentaDao.getById(id);
	}

	@Override
	public Integer getOrdenTrabajoIdByOrdenVenta(Integer id) {
		return ordenVentaDao.getOrdenTrabajoIdByOrdenVenta(id);
	}

}
