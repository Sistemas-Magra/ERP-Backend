package com.example.magra.erp.models.dao.talento_humano;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.talento_humano.Contrato;

public interface IContratoDao extends JpaRepository<Contrato, Integer> {
	@Query("SELECT c FROM Contrato c WHERE c.empleado.id = ?1")
	Contrato getContratoByEmpleado(Integer empleadoId);
	
	@Query(value="EXEC web_m002_sel_get_datos_contrato @empleado_id = ?1 , @mes = ?2 , @anio = ?3", nativeQuery=true)
	Map<String, Object> getDatosContrato(Integer empleadoId, Integer mes, Integer anio);
}
