package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.maestro.Moneda;

public interface IMonedaDao extends JpaRepository<Moneda, Integer> {
	
}
