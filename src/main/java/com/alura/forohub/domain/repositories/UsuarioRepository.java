package com.alura.forohub.domain.repositories;

import com.alura.forohub.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreoElectronico(String correo);
}
