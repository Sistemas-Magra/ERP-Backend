package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IMonedaDao;
import com.example.magra.erp.models.entity.maestro.Moneda;

@Service
public class MonedaServiceImpl implements IMonedaService {
	@Autowired
	private IMonedaDao monedaDao;

	@Override
	public List<Moneda> getAll() {
		return monedaDao.findAll();
	}

}
