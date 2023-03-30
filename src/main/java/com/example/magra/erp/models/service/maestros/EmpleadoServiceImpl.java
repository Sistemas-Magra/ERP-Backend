package com.example.magra.erp.models.service.maestros;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.maestros.IEmpleadoDao;
import com.example.magra.erp.models.entity.maestro.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {
	
	@Autowired
	private IEmpleadoDao empleadoDao;

	@Override
	public List<Empleado> autocomplete(String term) {
		return empleadoDao.autocomplete(term);
	}

	@Override
	public Integer cantidadEmpleados() {
		return empleadoDao.cantidadEmpleados();
	}

	@Override
	public List<Map<String, Object>> listadoMaestro(String nombre_completo, String nro_documento,
			String fecha_ingreso_desde, String fecha_ingreso_hasta, Integer ind_ver_inactivos, Integer page,
			Integer size) {
		return empleadoDao.listadoMaestro(nombre_completo, nro_documento, fecha_ingreso_desde, fecha_ingreso_hasta, 
				ind_ver_inactivos, page, size);
	}

	@Override
	public Empleado save(Empleado empleado) {
		return empleadoDao.save(empleado);
	}

	@Override
	public Integer cantidadCod(String codigo) {
		return empleadoDao.cantidadCod(codigo);
	}

	@Override
	public Empleado getById(Integer id) {
		return empleadoDao.getById(id);
	}

	@Override
	public void condicionarPersonal(Integer id, Integer estadoId) {
		empleadoDao.condicionarPersonal(id, estadoId);
	}

	@Override
	public void condicionarUsuario(Integer id, Integer estadoId) {
		empleadoDao.condicionarUsuario(id, estadoId);
	}

	@Override
	public void registrarVacaciones(String fechaInicio, String fechaFin, Integer empleadoId, Integer cantidadId,
			Integer usuarioId) {
		empleadoDao.registrarVacaciones(fechaInicio, fechaFin, empleadoId, cantidadId, usuarioId);
	}

}
