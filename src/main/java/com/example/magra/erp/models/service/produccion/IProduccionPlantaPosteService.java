package com.example.magra.erp.models.service.produccion;

import java.util.List;
import java.util.Map;


public interface IProduccionPlantaPosteService {
	List<Map<String, Object>> postesAutocomplete(String term, Integer ordenVentaId);
	List<Map<String, Object>> accesoriosAutocomplete(String term);
}
