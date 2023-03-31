package com.example.magra.erp.models.dao.talento_humano;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.talento_humano.Contrato;

public interface IContratoDao extends JpaRepository<Contrato, Integer> {
	@Query("SELECT c FROM Contrato c WHERE c.empleado.id = ?1")
	Contrato getContratoByEmpleado(Integer empleadoId);
}
