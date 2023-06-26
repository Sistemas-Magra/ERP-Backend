package com.example.magra.erp.models.dao.despacho;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.despacho.Despacho;

public interface IDespachoDao extends JpaRepository<Despacho, Integer> {
	@Query("SELECT d FROM Despacho d WHERE d.fecha = ?1")
	Despacho getDespachoByFecha(Date fecha);
}