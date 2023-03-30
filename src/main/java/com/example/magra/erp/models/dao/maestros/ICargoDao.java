package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.maestro.Cargo;

public interface ICargoDao extends JpaRepository<Cargo, Integer> {
	
}
