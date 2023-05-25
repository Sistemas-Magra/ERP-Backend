package com.example.magra.erp.models.service.gestion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.gestion.IEmpresaDao;
import com.example.magra.erp.models.entity.gestion.Empresa;

@Service
public class EmpresaServiceImpl implements IEmpresaService {
	
	@Autowired
	private IEmpresaDao empresaDao;

	@Override
	public List<Empresa> getEmpresasActivas() {
		return empresaDao.getEmpresasActivas();
	}

	@Override
	public Empresa getEmpresaById(Integer empresaId) {
		return empresaDao.getById(empresaId);
	}

}
