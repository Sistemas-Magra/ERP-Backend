package com.example.magra.erp.models.dao.gestion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.gestion.Empresa;

public interface IEmpresaDao extends JpaRepository<Empresa, Integer> {
	@Query("SELECT e FROM Empresa e WHERE e.estado.tablaAuxiliarDetalleId.id = 1")
	List<Empresa> getEmpresasActivas();
}