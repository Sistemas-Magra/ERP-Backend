package com.example.magra.erp.models.service.transporte;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.transporte.IEmpresaTransporteDao;
import com.example.magra.erp.models.entity.maestro.EmpresaTransporte;

@Service
public class EmpresaTransporteServiceImpl implements IEmpresaTransporteService {
	@Autowired
	private IEmpresaTransporteDao etDao;

	@Override
	public List<EmpresaTransporte> getAll() {
		return etDao.findAll();
	}

	@Override
	public EmpresaTransporte save(EmpresaTransporte empTrans) {
		return etDao.save(empTrans);
	}
	
}