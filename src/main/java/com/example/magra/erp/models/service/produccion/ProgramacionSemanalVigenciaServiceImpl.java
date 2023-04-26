package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProgramacionSemanalVigenciaDao;
import com.example.magra.erp.models.entity.produccion.ProgramacionSemanalVigencia;

@Service
public class ProgramacionSemanalVigenciaServiceImpl implements IProgramacionSemanalVigenciaService {
	@Autowired
	private IProgramacionSemanalVigenciaDao progSemVigDao;

	@Override
	public ProgramacionSemanalVigencia save(ProgramacionSemanalVigencia programacionSemanalVigencia) {
		return progSemVigDao.save(programacionSemanalVigencia);
	}

	@Override
	public ProgramacionSemanalVigencia getById(Integer id) {
		return progSemVigDao.getById(id);
	}

	@Override
	public List<Map<String, Object>> getMateriales(Integer id, Integer plantaId) {
		return progSemVigDao.getMateriales(id, plantaId);
	}
}