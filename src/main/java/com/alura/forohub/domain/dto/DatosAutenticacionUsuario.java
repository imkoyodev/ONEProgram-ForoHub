package com.alura.forohub.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        @Email
        String correoElectronico,
        @NotBlank
        String contrasena
) {
}
