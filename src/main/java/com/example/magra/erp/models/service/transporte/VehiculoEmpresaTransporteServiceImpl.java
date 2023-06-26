package com.example.magra.erp.models.service.transporte;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.transporte.IVehiculoEmpresaTransporteDao;
import com.example.magra.erp.models.entity.maestro.VehiculoEmpTrans;

@Service
public class VehiculoEmpresaTransporteServiceImpl implements IVehiculoEmpresaTransporteService {
	
	@Autowired
	private IVehiculoEmpresaTransporteDao vehiculoDao;

	@Override
	public VehiculoEmpTrans getByPlacas(String delantera, String trasera) {
		return vehiculoDao.getByPlacas(delantera, trasera);
	}

	@Override
	public VehiculoEmpTrans save(VehiculoEmpTrans vehiculo) {
		return vehiculoDao.save(vehiculo);
	}
	
	
}