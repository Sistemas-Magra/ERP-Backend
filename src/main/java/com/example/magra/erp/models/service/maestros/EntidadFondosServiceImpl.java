package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IEntidadFondosDao;
import com.example.magra.erp.models.entity.maestro.EntidadFondos;

@Service
public class EntidadFondosServiceImpl implements IEntidadFondosService {
	@Autowired
	private IEntidadFondosDao entidadFondosDao;

	@Override
	public List<EntidadFondos> findAll() {
		return entidadFondosDao.findAll();
	}

}
