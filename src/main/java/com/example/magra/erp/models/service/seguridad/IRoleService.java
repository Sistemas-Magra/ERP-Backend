package com.example.magra.erp.models.service.seguridad;

import java.util.List;

import com.example.magra.erp.models.entity.seguridad.Role;

public interface IRoleService {

	public List<Role> findAll();
	
	public Role findById(Integer id);

	List<Role> findByUserId(Integer userId);
	
	List<Role> autocompleteAsignacion(String term);

	List<Integer> listadoRolesUsuarios(Integer usuarioId);

	void updateRolesUsuarios(String listRoles, Integer isUsuario);
}