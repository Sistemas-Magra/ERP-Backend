package com.example.magra.erp.models.dao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Banco;

public interface IBancoDao extends JpaRepository<Banco, Integer>{
	@Query("SELECT b FROM Banco b WHERE b.estado.tablaAuxiliarDetalleId.id = 1")
	List<Banco> getBancosActivos();
}