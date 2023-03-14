package com.example.magra.erp.models.service.seguridad;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.magra.erp.models.dao.seguridad.IPasswordResetTokenDao;
import com.example.magra.erp.models.dao.seguridad.IUsuarioDao;
import com.example.magra.erp.models.entity.seguridad.PasswordResetToken;
import com.example.magra.erp.models.entity.seguridad.Usuario;
import com.example.magra.erp.models.entity.seguridad.UsuarioResponse;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private IPasswordResetTokenDao passwordResetTokenDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);

		if (usuario == null) {
			logger.error("Error en el login: no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException(
					"Error en el login: no existe el usuario '" + username + "' en el sistema");
		}
		
		List<GrantedAuthority> autorities = usuario.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority())).collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, autorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioById(Integer id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Usuario saveUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	public UsuarioResponse findUsuarioResponseByUsername(String username) {
		return usuarioDao.findUsuarioResponseByUsername(username);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public void createPasswordResetTokenForUsuario(Usuario usuario, String token) {
		PasswordResetToken myToken = new PasswordResetToken(token, usuario);
		passwordResetTokenDao.save(myToken);
	}

	@Override
	public PasswordResetToken findByToken(String token) {
		return passwordResetTokenDao.findByToken(token);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findUsuarioByCorreo(String correo, Integer user_id){
		return usuarioDao.findUserByCorreo(correo, user_id);
	}

}
