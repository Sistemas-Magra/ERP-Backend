package com.example.magra.erp.models.dao.auxiliares;

import org.springframework.data.repository.CrudRepository;

import com.example.magra.erp.models.entity.auxiliares.TablaAuxiliar;

public interface ITablaAuxiliarDao extends CrudRepository<TablaAuxiliar, String>{
	
	public TablaAuxiliar findByCodTablaAuxiliar(String codTablaAuxiliar);
	
}
