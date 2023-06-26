package com.example.magra.erp.controller.produccion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.service.produccion.IProduccionPlantaPosteService;

@RestController
@RequestMapping("/api")
public class ProduccionPlantaPosteRestController {
	@Autowired
	private IProduccionPlantaPosteService posteService;
	
	@GetMapping("/poste/autocomplete-acc/{term}")
	public List<Map<String, Object>> autocompleteAccesorios(@PathVariable String term) {
		return posteService.accesoriosAutocomplete(term);
	}
	
	@GetMapping("/poste/autocomplete/{ordenVentaId}/{term}")
	public List<Map<String, Object>> autocompletePostes(@PathVariable Integer ordenVentaId, @PathVariable String term) {
		return posteService.postesAutocomplete(term, ordenVentaId);
	}
}
