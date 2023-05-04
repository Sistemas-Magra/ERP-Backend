package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IEncargadoPlantaDao;

@Service
public class EncargadoPlantaServiceImpl implements IEncargadoPlantaService {
	@Autowired
	private IEncargadoPlantaDao encargadoPlantaDao;

	@Override
	public List<Map<String, Object>> getEncargadosAsignados() {
		return encargadoPlantaDao.getEncargadosAsignados();
	}

	@Override
	public List<Map<String, Object>> getEncargadosAsignadosPorPlanta(Integer plantaId) {
		return encargadoPlantaDao.getEncargadosAsignadosPorPlanta(plantaId);
	}

	@Override
	public void insertEncargado(Integer idUsuario, Integer plantaId) {
		encargadoPlantaDao.insertEncargado(idUsuario, plantaId);
	}
}