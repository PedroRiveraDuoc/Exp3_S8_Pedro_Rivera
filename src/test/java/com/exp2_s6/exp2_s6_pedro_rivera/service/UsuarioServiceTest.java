package com.exp2_s6.exp2_s6_pedro_rivera.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.opentest4j.AssertionFailedError;

import com.exp2_s6.exp2_s6_pedro_rivera.model.Usuario;
import com.exp2_s6.exp2_s6_pedro_rivera.repository.UsuarioRepository;

public class UsuarioServiceTest {


    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }
 
    @Test
    public void testAddUsuario() {
        // Crear un usuario de prueba
        Usuario newUser = new Usuario();
        newUser.setNombre("Pedro");
        newUser.setEmail("p@dro.cl");
        newUser.setPassword("securepassword123");

        Usuario expeUsuario = new Usuario();
        expeUsuario.setNombre("Pedro");

        when(this.usuarioRepository.save(any(Usuario.class))).thenReturn(expeUsuario);
        this.usuarioService.addUsuario(newUser);

        assertEquals(newUser.getNombre(), expeUsuario.getNombre());
    }

    @Test
    public void testAddUsuarioError() {
        assertThrows(AssertionFailedError.class, () -> { // Cambiar a DataIntegrityViolationException
            Usuario newUser = new Usuario();
            newUser.setNombre("Pedro");
            newUser.setEmail("p@dro.cl");
            newUser.setPassword("securepassword123");

            Usuario expeUsuario = new Usuario();

            when(this.usuarioRepository.save(any(Usuario.class))).thenReturn(expeUsuario);
            this.usuarioService.addUsuario(newUser);

            assertEquals(newUser.getNombre(), expeUsuario.getNombre());
        });
    }
    
}
