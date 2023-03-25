package com.example.magra.erp.models.dao.seguridad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.magra.erp.models.entity.seguridad.Role;

public interface IRoleDao extends JpaRepository<Role, Integer>{
	@Query("SELECT u.roles FROM Usuario u WHERE u.id = ?1")
	List<Role> findByUserId(Integer userId);

	@Query("SELECT r FROM Role r WHERE UPPER(r.nombreDetallado) LIKE UPPER(CONCAT('%', ?1 , '%'))")
	List<Role> autocompleteAsignacion(String term);
	
	@Query(value="SELECT rol.seg_rol_id FROM seg_usuario_rol rol WHERE rol.seg_usuario_id = ?1", nativeQuery=true)
	List<Integer> listadoRolesUsuarios(Integer usuarioId);
	
	@Query(value="EXEC web_m001_man_update_roles @listRoles = ?1 , @idUsuario = ?2", nativeQuery=true)
	void updateRolesUsuarios(String listRoles, Integer isUsuario);
}