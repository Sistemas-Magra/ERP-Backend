package com.example.magra.erp.models.service.seguridad;

import java.util.List;
import java.util.Map;

import com.example.magra.erp.models.entity.seguridad.PasswordResetToken;
import com.example.magra.erp.models.entity.seguridad.Usuario;
import com.example.magra.erp.models.entity.seguridad.UsuarioResponse;

public interface IUsuarioService {
	UsuarioResponse findUsuarioResponseByUsername(String username);
	Usuario findByUsername(String username);
	
	public Usuario findUsuarioById(Integer id);
	
	public Usuario saveUsuario(Usuario usuario);
	
	public void createPasswordResetTokenForUsuario(Usuario usuario, String token);
	
	public PasswordResetToken findByToken(String token);
	
	public Usuario findUsuarioByCorreo(String correo, Integer user_id);
	
	Map<String, Object> registrarUsuario(String json);

	List<Map<String, Object>> listMaestro(String nombre, String username, String correo, Integer indVerInactivos);

	void inactivarUsuario(Integer id, Integer ind);
	
	Integer validUsuarioExistente(String username, String correo);
	Map<String,Object> getDatosEmpleadoFromUsuario(Integer id);
}
