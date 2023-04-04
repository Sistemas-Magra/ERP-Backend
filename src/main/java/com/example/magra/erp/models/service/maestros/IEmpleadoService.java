package com.example.magra.erp.models.service.maestros;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.maestro.Empleado;

public interface IEmpleadoService {
	List<Empleado> autocomplete(String term);
	Integer cantidadEmpleados();
	List<Map<String, Object>> listadoMaestro(String nombre_completo, String nro_documento, String fecha_ingreso_desde, 
			String fecha_ingreso_hasta, Integer ind_ver_inactivos, Integer page, Integer size);
	Empleado save(Empleado empleado);
	Integer cantidadCod(String codigo);
	Empleado getById(Integer id);
	void condicionarPersonal(Integer id, Integer estadoId);
	void condicionarUsuario(Integer id, Integer estadoId);
	void registrarVacaciones(String fechaInicio, String fechaFin, Integer empleadoId, Integer cantidadId, Integer usuarioId);
	List<Map<String, Object>> getListActivos(String fecha, Integer indVerInactivo);
	Integer getByIdentidad(Integer tipoDocumentoId, String nroDocumento);
}
