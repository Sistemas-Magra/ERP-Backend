package com.example.magra.erp.models.dao.seguridad;

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
}
