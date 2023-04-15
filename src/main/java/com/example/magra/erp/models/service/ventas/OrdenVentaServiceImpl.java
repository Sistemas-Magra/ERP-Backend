package com.example.magra.erp.models.service.ventas;

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

}
