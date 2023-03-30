package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.Cargo;

public interface ICargoService {
	List<Cargo> findAll();
}
