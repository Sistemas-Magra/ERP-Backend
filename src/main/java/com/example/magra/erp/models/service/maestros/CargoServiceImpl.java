package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.ICargoDao;
import com.example.magra.erp.models.entity.maestro.Cargo;

@Service
public class CargoServiceImpl implements ICargoService {
	@Autowired
	private ICargoDao cargoDao;

	@Override
	public List<Cargo> findAll() {
		return cargoDao.findAll();
	}
}