package com.example.magra.erp.models.service.ventas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.ventas.IPagoDao;
import com.example.magra.erp.models.entity.ventas.Pago;

@Service
public class PagoServiceImpl implements IPagoService {
	@Autowired
	private IPagoDao pagoDao;

	@Override
	public Pago save(Pago pago) {
		return pagoDao.save(pago);
	}

	@Override
	public List<Pago> getPagosFromOrdenVenta(Integer id) {
		return pagoDao.getPagosFromOrdenVenta(id);
	}
}