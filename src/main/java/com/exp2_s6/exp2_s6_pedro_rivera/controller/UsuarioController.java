package com.exp2_s6.exp2_s6_pedro_rivera.controller;

import com.exp2_s6.exp2_s6_pedro_rivera.model.Usuario;
import com.exp2_s6.exp2_s6_pedro_rivera.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Cambiar para usar HATEOAS
    // Endpoint para obtener todos los usuarios registrados.
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    // Endpoint para obtener un usuario específico por ID.
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable @Min(1) int id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para agregar un nuevo usuario a la lista.
    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.addUsuario(usuario);
        return ResponseEntity.ok(savedUsuario);
    }

    // Endpoint para actualizar un usuario existente por ID.
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable @Min(1) int id, @Valid @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario updatedUser = usuarioService.updateUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error al actualizar el usuario: " + ex.getMessage());
        }
    }

    // Endpoint para eliminar un usuario existente por ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable @Min(1) int id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.ok().body("Usuario eliminado exitosamente.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + ex.getMessage());
        }
    }

    // Endpoint para login.
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        boolean isValid = usuarioService.validarLogin(email, password);
        if (isValid) {
            return ResponseEntity.ok("Login exitoso!");
        } else {
            return ResponseEntity.status(401).body("Login fallido: Credenciales inválidas");
        }
    }
}
