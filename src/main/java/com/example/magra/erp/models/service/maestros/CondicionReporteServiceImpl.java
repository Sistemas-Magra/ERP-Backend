package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.ICondicionReporteDao;
import com.example.magra.erp.models.entity.maestro.CondicionReporte;

@Service
public class CondicionReporteServiceImpl implements ICondicionReporteService {
	@Autowired
	private ICondicionReporteDao condicionDao;

	@Override
	public List<CondicionReporte> getAllCondiciones() {
		return condicionDao.findAll();
	}

	@Override
	public CondicionReporte save(CondicionReporte condicion) {
		return condicionDao.save(condicion);
	}
}
