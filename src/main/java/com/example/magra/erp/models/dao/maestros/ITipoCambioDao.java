package com.example.magra.erp.models.dao.maestros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.TipoCambio;

public interface ITipoCambioDao extends JpaRepository<TipoCambio, Integer>{
	@Query(value="SELECT * FROM mae_tipo_cambio WHERE CONVERT(DATE, fecha) = CONVERT(DATE, GETDATE())", nativeQuery=true)
	TipoCambio getUltimoTipoCambio();
}