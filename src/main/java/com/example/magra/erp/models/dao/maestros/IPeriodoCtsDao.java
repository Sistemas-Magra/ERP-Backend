package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.PeriodoCts;

public interface IPeriodoCtsDao extends JpaRepository<PeriodoCts, Integer>{
	@Query("SELECT cts FROM PeriodoCts cts WHERE cts.estado.tablaAuxiliarDetalleId.id = 1")
	PeriodoCts getPeriodoCtsActivo();
}
