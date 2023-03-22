package com.example.magra.erp.controller.seguridad;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.magra.erp.VariablesGlobales;
import com.example.magra.erp.models.entity.seguridad.ChangedPassword;
import com.example.magra.erp.models.entity.seguridad.PasswordResetToken;
import com.example.magra.erp.models.entity.seguridad.Role;
import com.example.magra.erp.models.entity.seguridad.Usuario;
import com.example.magra.erp.models.service.IEmailService;
//import com.example.magra.erp.models.service.IUploadFileService;
import com.example.magra.erp.models.service.auxiliares.IConfiguracionService;
import com.example.magra.erp.models.service.seguridad.IPasswordResetTokenService;
import com.example.magra.erp.models.service.seguridad.IRoleService;
import com.example.magra.erp.models.service.seguridad.IUsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IPasswordResetTokenService passwordResetTokenService;
/*
	@Autowired
	private IUploadFileService uploadService;*/

	@SuppressWarnings("unused")
	@Autowired
	private IConfiguracionService configurationService;

	@GetMapping("/role/all")
	public List<Role> getAllModulo() {
		return roleService.findAll();
	}

	@GetMapping("/role/{id}")
	public Role getAllModulo(@PathVariable Integer id) {
		return roleService.findById(id);
	}

	@PostMapping("/usuario/resetPassword")
	public ResponseEntity<?> resetPassword(@RequestBody String userEmail) {

		Map<String, Object> response = new HashMap<>();

		Usuario user = usuarioService.findUsuarioByCorreo(userEmail, 0);

		if (user == null) {
			response.put("mensaje", "Error: el usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		String token = UUID.randomUUID().toString();
		usuarioService.createPasswordResetTokenForUsuario(user, token);

		String content = "Estimado usuario " + user.getUsername() + ",\n" + "Para cambiar su contraseña, "
				+ "ingrese al siguiente link para restaurar su contraseña :" + "\n" + VariablesGlobales.RUTA_FRONTEND
				+ "/user/reestablecerPassword/" + token + "\n\n" + "Gracias.";

		try {
			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			emailService.sendEmail(user.getEmail(), "Cambio de contraseña " + date.format(format), content, "", null,
					null);

		} catch (Exception e) {
			response.put("mensaje", "Error al enviar el correo");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "Se envió el correo exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("/usuario/comprobar/{token}")
	public ResponseEntity<?> showChangePasswordPage(@PathVariable String token) {

		Map<String, Object> response = new HashMap<>();
		String result = "El resultado es ";
		PasswordResetToken passToken = usuarioService.findByToken(token);

		if (!isTokenValidado(passToken)) {
			result += ": token invalido";
			response.put("mensaje", result);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		} else {
			result += ": token valido";
			response.put("mensaje", result);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.ACCEPTED);
		}
	}

	@PutMapping("/usuario/changedPassword/{token}/{password}")
	public ResponseEntity<?> update(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result,
			@PathVariable String token, @PathVariable String password) {

		PasswordResetToken passToken = usuarioService.findByToken(token);
		Usuario usuarioActual = null;
		Usuario usuarioUpdate = null;
		usuarioActual = usuarioService.findUsuarioById(passToken.getUser().getId());

		Map<String, Object> response = new HashMap<>();

		if (!isTokenValidado(passToken)) {
			response.put("mensaje", "Error, el token es invalido");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
		}

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			usuarioActual.setPassword(passwordEncoder.encode(password));
			usuarioActual.setEnabled(true);
			usuarioUpdate = usuarioService.saveUsuario(usuarioActual);

			passToken.setEnabled(false);
			passToken.setUser(usuarioUpdate);
			passwordResetTokenService.save(passToken);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "ControlEnvioDetalle actualizada exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuario/password/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findUsuarioById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			usuarioActual.setPassword(passwordEncoder.encode(changedPassword.getPassword()));
			
			usuarioService.saveUsuario(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Datos actualizado exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuario/datos/{id}")
	public ResponseEntity<?> updateDatos(@Valid @RequestBody ChangedPassword changedPassword, BindingResult result, @PathVariable Integer id) {
		Usuario usuarioActual = usuarioService.findUsuarioById(id);
		
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error, no se pudo editar: El usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			usuarioActual.setEmail(changedPassword.getCorreoNuevo());
			
			usuarioService.saveUsuario(usuarioActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la BD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Datos actualizado exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/usuario/validateUser")
	public ResponseEntity<?> validatePassword(@RequestBody Integer userId) {
		
		Map<String, Object> response = new HashMap<>();
		
	    Usuario user = usuarioService.findUsuarioById(userId);
	    		
	    if(user == null) {
	    	response.put("mensaje", "Error: el usuario no existe en la base de datos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }
	      
	    String token = UUID.randomUUID().toString();
	    usuarioService.createPasswordResetTokenForUsuario(user, token);
	    
	    String content = "";
	    
	    content = "Hola "+user.getApellidoPaterno()+ " " + user.getApellidoMaterno() + ", " + user.getNombres() +"\n"
	 	    		+ "¡Felicitaciones! Su cuenta se creó exitosamente."+"\n"
	 	    		+ "Su nombre de usuario es: "+user.getUsername()+"\n"
	 	    		+ "Por favor de click sobre el enlace que figura a continuación para que usted genere su propia contraseña."+"\n"
	 	    		+ VariablesGlobales.RUTA_FRONTEND +"/user/reestablecerPassword/"+token+"\n"
	 	    		+ "\n"
	 	    		+ "Cordialmente"+ "\n"
	 	    		+ "Farmacia Universal";

	    try {
	    	emailService.sendEmail(user.getEmail(),"Correo electrónico de bienvenida", content,"", null, null);
	    }catch(Exception e) {
	    	response.put("mensaje", "Error al enviar el correo");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    response.put("mensaje", "Se envió el correo exitosamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);     
	}

	private boolean isTokenValidado(PasswordResetToken passToken) {
		Date fecha = new Date();

		if (passToken == null) {
			return false;
		}

		if (passToken.getUser() == null) {
			return false;
		}

		if (!passToken.getUser().getEnabled()) {
			return false;
		}

		if (passToken.getExpiryDate().getTime() < fecha.getTime()) {
			return false;
		}

		if (!passToken.getEnabled()) {
			return false;
		}

		return true;
	}

}