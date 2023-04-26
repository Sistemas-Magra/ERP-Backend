package com.example.magra.erp.models.dao.produccion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.produccion.OrdenTrabajo;

public interface IOrdenTrabajoDao extends JpaRepository<OrdenTrabajo, Integer>{
	@Query("SELECT COUNT(ot) FROM OrdenTrabajo ot")
	Integer getCatOT();
	
	@Query("SELECT ot "
			+ " FROM OrdenTrabajo ot "
			+ " WHERE (UPPER(ot.codigo) LIKE UPPER(CONCAT('%', ?1 , '%')) OR UPPER(ot.ordenVenta.codigo) LIKE UPPER(CONCAT('%', ?1 , '%'))) "
			+ " AND ot.estado.tablaAuxiliarDetalleId.id IN (1, 2)")
	List<OrdenTrabajo> autocomplete(String term);
}