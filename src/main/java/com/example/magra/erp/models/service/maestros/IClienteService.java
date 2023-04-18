package com.example.magra.erp.models.service.maestros;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.maestro.Cliente;

public interface IClienteService {
	Map<String, Object> getFromSunat(String nroDocumento, Integer ind);
	List<Cliente> getClientesAutocomplete(String term);
	Cliente save(Cliente cliente);
}
