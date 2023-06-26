package com.example.magra.erp.models.service.despacho;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.despacho.Formulario;

public interface IFormularioService {
	Formulario save(Formulario formulario);
	List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido);
	Formulario getById(Integer id);
}