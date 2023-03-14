package com.example.magra.erp.models.service.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.magra.erp.models.dao.seguridad.ISubModuloDao;
import com.example.magra.erp.models.entity.seguridad.SubModulo;

@Repository
public class SubModuloImpl implements ISubModuloService {

	@Autowired
	private ISubModuloDao subModuloDao;
	
	@Override
	public List<SubModulo> findAll() {
		return subModuloDao.findAll();
	}

	@Override
	public List<SubModulo> findAllByIdModulo(Integer id) {
		return subModuloDao.getSubModuloByIdModulo(id);
	}

}