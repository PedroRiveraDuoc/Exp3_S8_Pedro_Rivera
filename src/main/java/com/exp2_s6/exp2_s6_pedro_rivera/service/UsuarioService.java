package com.exp2_s6.exp2_s6_pedro_rivera.service;

import com.exp2_s6.exp2_s6_pedro_rivera.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario addUsuario(Usuario usuario);
    Usuario updateUsuario(int id, Usuario usuarioDetails);
    void deleteUsuario(int id);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(int id);
    
    // Método para validar el login
    boolean validarLogin(String email, String password);
}
