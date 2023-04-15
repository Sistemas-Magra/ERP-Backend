package com.example.magra.erp.controller.maestros;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.models.entity.maestro.Cliente;
import com.example.magra.erp.models.service.maestros.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/cliente/sunat/{ruc}/{ind}")
	public Map<String, Object> getClienteFromSunat(@PathVariable String ruc, @PathVariable Integer ind){
		return clienteService.getFromSunat(ruc, ind);
	}
	
	@GetMapping("/cliente/autocomplete/{term}")
	public List<Cliente> getClientesAutocomplete(@PathVariable String term) {
		return clienteService.getClientesAutocomplete(term);
	}
}
