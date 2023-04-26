package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProgramacionSemanalDao;
import com.example.magra.erp.models.entity.produccion.ProgramacionSemanal;

@Service
public class ProgramacionSemanalServiceImpl implements IProgramacionSemanalService {
	@Autowired
	private IProgramacionSemanalDao progSemDao;

	@Override
	public ProgramacionSemanal save(ProgramacionSemanal progSem) {
		return progSemDao.save(progSem);
	}

	@Override
	public List<Map<String, Object>> getListadoProgramaciones(Integer anio, Integer mes) {
		return progSemDao.getListadoProgramaciones(anio, mes);
	}

	@Override
	public ProgramacionSemanal getById(Integer id) {
		return progSemDao.getById(id);
	}

	@Override
	public List<Map<String, Object>> getListadoVersiones(Integer progVigId) {
		return progSemDao.getListadoVersiones(progVigId);
	}
}
