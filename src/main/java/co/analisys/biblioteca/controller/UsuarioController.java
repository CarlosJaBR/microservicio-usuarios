package co.analisys.biblioteca.controller;

import co.analisys.biblioteca.model.Email;
import co.analisys.biblioteca.model.Usuario;
import co.analisys.biblioteca.model.UsuarioId;
import co.analisys.biblioteca.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
        summary = "Obtener usuario por ID",
        description = "Devuelve la información del usuario si tiene el rol USER"
    )
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Usuario obtenerUsuario(@PathVariable String id) {
        return usuarioService.obtenerUsuario(new UsuarioId(id));
    }

    @Operation(
        summary = "Cambiar email del usuario",
        description = "Permite cambiar el email del usuario"
    )
    @PutMapping("/{id}/email")
    @PreAuthorize("hasRole('ROLE_USER)")
    public void cambiarEmail(
        @PathVariable String id,
        @RequestBody String nuevoEmail
    ) {
        usuarioService.cambiarEmailUsuario(
            new UsuarioId(id),
            new Email(nuevoEmail)
        );
    }
}
