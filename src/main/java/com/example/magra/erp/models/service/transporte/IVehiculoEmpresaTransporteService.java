package com.example.magra.erp.models.service.transporte;

import com.example.magra.erp.models.entity.maestro.VehiculoEmpTrans;

public interface IVehiculoEmpresaTransporteService {

	VehiculoEmpTrans getByPlacas(String delantera, String trasera);
	VehiculoEmpTrans save(VehiculoEmpTrans vehiculo);
}