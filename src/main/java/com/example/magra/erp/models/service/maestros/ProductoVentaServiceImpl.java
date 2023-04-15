package com.example.magra.erp.models.service.maestros;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IProductoVentaDao;
import com.example.magra.erp.models.entity.maestro.ProductoVenta;

@Service
public class ProductoVentaServiceImpl implements IProductoVentaService
{
	@Autowired
	private IProductoVentaDao productoVentaDao;

	@Override
	public List<ProductoVenta> autocomplete(String term) {
		return productoVentaDao.autocomplete(term);
	}
}