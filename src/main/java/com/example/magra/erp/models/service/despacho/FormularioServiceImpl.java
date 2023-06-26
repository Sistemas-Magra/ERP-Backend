package com.example.magra.erp.models.service.despacho;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.despacho.IFormularioDao;
import com.example.magra.erp.models.entity.despacho.Formulario;

@Service
public class FormularioServiceImpl implements IFormularioService {
	
	@Autowired
	private IFormularioDao formularioDao;

	@Override
	public Formulario save(Formulario formulario) {
		return formularioDao.save(formulario);
	}

	@Override
	public List<Map<String, Object>> getListadoFormularios(String fecha, String cliente, String nroPedido) {
		return formularioDao.getListadoFormularios(fecha, cliente, nroPedido);
	}

	@Override
	public Formulario getById(Integer id) {
		return formularioDao.getById(id);
	}

}