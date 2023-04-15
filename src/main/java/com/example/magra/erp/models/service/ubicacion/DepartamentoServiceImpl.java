package com.example.magra.erp.models.service.ubicacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.ubicacion.IDepartamentoDao;
import com.example.magra.erp.models.entity.ubicacion.Departamento;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {
	@Autowired
	private IDepartamentoDao departamentoDao;

	@Override
	public List<Departamento> findAll() {
		return departamentoDao.findAll();
	}
}