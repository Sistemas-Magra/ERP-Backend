package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.ICaracteristicaReporteDao;
import com.example.magra.erp.models.entity.maestro.CaracteristicaReporte;

@Service
public class CaracteristicaReporteServiceImpl implements ICaracteristicaReporteService {
	@Autowired
	private ICaracteristicaReporteDao caracteristicaDao;

	@Override
	public List<CaracteristicaReporte> getAllCaracterísticas() {
		return caracteristicaDao.findAll();
	}

	@Override
	public CaracteristicaReporte save(CaracteristicaReporte caracteristica) {
		return caracteristicaDao.save(caracteristica);
	}
}
