package com.example.magra.erp.models.dao.ventas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.ventas.Pago;

public interface IPagoDao extends JpaRepository<Pago, Integer> {
	@Query("SELECT ov.pagos FROM OrdenVenta ov WHERE ov.id = ?1")
	List<Pago> getPagosFromOrdenVenta(Integer id);
}