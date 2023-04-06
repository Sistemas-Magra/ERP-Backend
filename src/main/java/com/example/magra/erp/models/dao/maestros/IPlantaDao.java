package com.example.magra.erp.models.dao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Planta;

public interface IPlantaDao extends JpaRepository<Planta, Integer> {
	@Query("SELECT p FROM Planta p WHERE p.estado.tablaAuxiliarDetalleId.id = 1")
	List<Planta> getPlantasActivas();
}
