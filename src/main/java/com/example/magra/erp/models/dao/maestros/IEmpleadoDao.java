package com.example.magra.erp.models.dao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Integer> {
	@Query("SELECT e FROM Empleado e WHERE UPPER(e.nombreCompleto) LIKE UPPER(CONCAT('%', ?1 , '%')) ")
	List<Empleado> autocomplete(String term);
}