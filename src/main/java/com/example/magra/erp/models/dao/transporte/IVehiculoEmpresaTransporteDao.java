package com.example.magra.erp.models.dao.transporte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.VehiculoEmpTrans;

public interface IVehiculoEmpresaTransporteDao extends JpaRepository<VehiculoEmpTrans, Integer> {
	@Query("SELECT v FROM VehiculoEmpTrans v WHERE v.placaDelantera = ?1 AND v.placaTrasera = ?2")
	VehiculoEmpTrans getByPlacas(String delantera, String trasera);
}