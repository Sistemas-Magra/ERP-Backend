package com.example.magra.erp.models.dao.ventas;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.magra.erp.models.entity.ventas.OrdenVenta;

public interface IOrdenVentaDao extends JpaRepository<OrdenVenta, Integer> {
	
}
