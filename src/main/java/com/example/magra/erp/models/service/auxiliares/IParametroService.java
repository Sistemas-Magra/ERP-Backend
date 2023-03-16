package com.example.magra.erp.models.service.auxiliares;

import java.util.List;

import com.example.magra.erp.models.entity.auxiliares.Parametro;

public interface IParametroService {
	
	public Parametro getParametroById(Integer id);
	
	public Parametro getValorIntervalo();
	
	public List<Parametro> getAllParametros();
	
	public Parametro save(Parametro parametro);

}