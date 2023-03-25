package com.example.magra.erp.models.service.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.magra.erp.models.dao.seguridad.IRoleDao;
import com.example.magra.erp.models.entity.seguridad.Role;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}

	@Override
	public List<Role> findByUserId(Integer userId) {
		return roleDao.findByUserId(userId);
	}

	@Override
	public List<Role> autocompleteAsignacion(String term) {
		return roleDao.autocompleteAsignacion(term);
	}

	@Override
	public List<Integer> listadoRolesUsuarios(Integer usuarioId) {
		return roleDao.listadoRolesUsuarios(usuarioId);
	}

	@Override
	public void updateRolesUsuarios(String listRoles, Integer isUsuario) {
		roleDao.updateRolesUsuarios(listRoles, isUsuario);
	}

}