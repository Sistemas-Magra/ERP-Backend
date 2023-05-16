package com.example.magra.erp.models.dao.maestros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.ProductoVenta;

public interface IProductoVentaDao extends JpaRepository<ProductoVenta, Integer> {
	@Query("SELECT pv FROM ProductoVenta pv WHERE UPPER(CONCAT(pv.nombre)) LIKE UPPER(CONCAT('%', ?1 , '%'))")
	List<ProductoVenta> autocomplete(String term);
}
