package com.example.magra.erp.models.dao.maestros;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.maestro.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Integer> {
	@Query("SELECT e FROM Empleado e WHERE UPPER(e.nombreCompleto) LIKE UPPER(CONCAT('%', ?1 , '%')) ")
	List<Empleado> autocomplete(String term);
	
	@Query(value="EXEC web_m002_sel_listado_empleado "
			+ " @nombre_completo = ?1 , "
			+ " @nro_documento = ?2 , "
			+ " @fecha_ingreso_desde = ?3, "
			+ " @fecha_ingreso_hasta = ?4, "
			+ " @ind_ver_inactivos = ?5 , "
			+ " @page = ?6 , "
			+ " @size = ?7", nativeQuery=true)
	List<Map<String, Object>> listadoMaestro(String nombre_completo, String nro_documento, String fecha_ingreso_desde, 
			String fecha_ingreso_hasta, Integer ind_ver_inactivos, Integer page, Integer size);
	
	@Query("SELECT COUNT(e.id) FROM Empleado e")
	Integer cantidadEmpleados();
	
	@Query("SELECT COUNT(e.id) FROM Empleado e WHERE UPPER(e.codigo) LIKE UPPER(CONCAT('%', ?1 , '%'))")
	Integer cantidadCod(String codigo);
	
	@Query("SELECT e FROM Empleado e WHERE e.id = ?1")
	Empleado getById(Integer id);
	
	@Query(value="UPDATE mae_empleado SET estado_id = ?2 WHERE id = ?1 SELECT 1", nativeQuery=true)
	void condicionarPersonal(Integer id, Integer estadoId);
	
	@Query(value="UPDATE sti_usuario SET estado_id = ?2 , enabled = ?2 WHERE empleado_id = ?1 SELECT 1", nativeQuery=true)
	void condicionarUsuario(Integer id, Integer estadoId);
	
	@Query(value="EXEC web_m002_man_registrar_vacaciones "
			+ " @fecha_inicio = ?1 , "
			+ " @fecha_fin = ?2 , "
			+ " @empleado_id = ?3 , "
			+ " @cantidad_dias = ?4 , "
			+ " @id_usuario = ?5 ", nativeQuery=true)
	void registrarVacaciones(String fechaInicio, String fechaFin, Integer empleadoId, Integer cantidadId, Integer usuarioId);
	
	@Query(value="EXEC web_m002_sel_get_listado_asistencia @fecha = ?1 , @ind_ver_inactivo = ?2", nativeQuery=true)
	List<Map<String, Object>> getListActivos(String fecha, Integer indVerInactivo);
}