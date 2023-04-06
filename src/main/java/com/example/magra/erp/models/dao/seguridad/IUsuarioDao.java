package com.example.magra.erp.models.dao.seguridad;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.seguridad.Usuario;
import com.example.magra.erp.models.entity.seguridad.UsuarioResponse;

public interface IUsuarioDao extends JpaRepository<Usuario, Integer>{
	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario findByUsername(String username);

	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	UsuarioResponse findUsuarioResponseByUsername(String username);
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findUserByCorreo(String email, Integer user_id);
	
	@Query(value="EXEC web_m001_man_registrar_empleado @json = ?1", nativeQuery=true)
	Map<String, Object> registrarUsuario(String json);
	
	@Query(value="EXEC web_m001_sel_listado_usuarios @nombre = ?1 , @username = ?2 , @correo = ?3 , @indVerInactivos = ?4", nativeQuery=true)
	List<Map<String, Object>> listMaestro(String nombre, String username, String correo, Integer indVerInactivos);
	
	@Query(value="UPDATE sti_usuario SET estado_id = ?2 , enabled = ?2 WHERE id = ?1 ; SELECT 1", nativeQuery=true)
	void inactivarUsuario(Integer id, Integer ind);
	
	@Query("SELECT COUNT(u.id) FROM Usuario u WHERE u.username = ?1 OR u.email = ?2")
	Integer validUsuarioExistente(String username, String correo);
	
	@Query(value=""
			+ " SELECT emp.id, emp.nombre_completo, car.nombre AS cargo "
			+ " FROM sti_usuario usu "
			+ " JOIN mae_empleado emp ON emp.id = usu.empleado_id "
			+ "	JOIN mae_cargo car ON car.id = emp.cargo_id "
			+ " WHERE usu.id = ?1", nativeQuery=true)
	Map<String,Object> getDatosEmpleadoFromUsuario(Integer id);
}
