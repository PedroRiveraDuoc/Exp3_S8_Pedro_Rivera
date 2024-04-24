package com.exp2_s6.exp2_s6_pedro_rivera.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.exp2_s6.exp2_s6_pedro_rivera.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar un usuario por email
    Optional<Usuario> findByEmail(String email);
}
