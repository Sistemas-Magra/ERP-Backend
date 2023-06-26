package com.example.magra.erp.models.service.transporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.transporte.IConductorEmpresaTransporteDao;
import com.example.magra.erp.models.entity.maestro.ConductorEmpTrans;

@Service
public class ConductorEmpresaTransporteServiceImpl implements IConductorEmpresaTransporteService {
	
	@Autowired
	private IConductorEmpresaTransporteDao conductorDao;

	@Override
	public ConductorEmpTrans save(ConductorEmpTrans conductor) {
		return conductorDao.save(conductor);
	}

	@Override
	public ConductorEmpTrans getByConductor(String licencia) {
		return conductorDao.getByConductor(licencia);
	}
}