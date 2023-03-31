package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.PeriodoGratificacion;

public interface IPeriodoGratificacionDao extends JpaRepository<PeriodoGratificacion, Integer> {
	@Query("SELECT gr FROM PeriodoGratificacion gr WHERE gr.estado.tablaAuxiliarDetalleId.id = 1")
	PeriodoGratificacion getPeriodoActivo();
}
