package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.produccion.IProduccionPlantaPosteDao;

@Service
public class ProduccionPlantaPosteServiceImpl implements IProduccionPlantaPosteService {
	@Autowired
	private IProduccionPlantaPosteDao posteDao;

	@Override
	public List<Map<String, Object>> postesAutocomplete(String term, Integer ordenVentaId) {
		return posteDao.postesAutocomplete(term, ordenVentaId);
	}

	@Override
	public List<Map<String, Object>> accesoriosAutocomplete(String term) {
		return posteDao.accesoriosAutocomplete(term);
	}
	
	
}