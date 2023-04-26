package com.example.magra.erp.models.service.ventas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.ventas.IOrdenVentaDetalleDao;

@Service
public class OrdenVentaDetalleImpl implements IOrdenVentaDetalleService {
	
	@Autowired
	private IOrdenVentaDetalleDao detalleDao;

	@Override
	public void updateFilenames(String plano, String esp, Integer number) {
		detalleDao.updateFilenames(plano, esp, number);
	}

}
