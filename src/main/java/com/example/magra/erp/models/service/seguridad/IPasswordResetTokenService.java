package com.example.magra.erp.models.service.seguridad;

import com.example.magra.erp.models.entity.seguridad.PasswordResetToken;

public interface IPasswordResetTokenService {

	public PasswordResetToken findByToken(String token);
	public PasswordResetToken save(PasswordResetToken passwordResetToken);

}
