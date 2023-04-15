package com.example.magra.erp.models.service.maestros;

import java.util.List;

import com.example.magra.erp.models.entity.maestro.ProductoVenta;

public interface IProductoVentaService {
	List<ProductoVenta> autocomplete(String term);
}