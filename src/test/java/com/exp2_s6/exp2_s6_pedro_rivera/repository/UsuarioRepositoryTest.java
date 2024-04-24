package com.exp2_s6.exp2_s6_pedro_rivera.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exp2_s6.exp2_s6_pedro_rivera.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

    // Prueba de integración para el repositorio de usuarios
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindByEmail() {
        // Crear y guardar un usuario de prueba
        Usuario newUser = new Usuario();
        newUser.setNombre("Pedro");
        newUser.setEmail("pedro@example.com");
        newUser.setPassword("securepassword123");
        usuarioRepository.save(newUser);

        // Buscar el usuario por email
        Optional<Usuario> foundUser = usuarioRepository.findByEmail("pedro@example.com");

        // Verificar que el usuario se encuentre y que los datos sean correctos
        assertTrue(foundUser.isPresent(), "El usuario debe ser encontrado");
        assertEquals("Pedro", foundUser.get().getNombre(), "El nombre del usuario debe coincidir");
        assertEquals("pedro@example.com", foundUser.get().getEmail(), "El email del usuario debe coincidir");
    }
}
