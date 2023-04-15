package com.example.magra.erp.models.dao.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.ubicacion.Departamento;

public interface IDepartamentoDao extends JpaRepository<Departamento, Integer> {
	
}