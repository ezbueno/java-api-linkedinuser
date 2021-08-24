package com.buenoezandro.linkedIn.api.controllers;

import com.buenoezandro.linkedIn.api.entities.Usuario;
import com.buenoezandro.linkedIn.api.services.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.CREATED)
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return this.usuarioService.criar(usuario);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> buscarUsuario() {
        return this.usuarioService.buscar();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(this.usuarioService.buscarPorId(id));
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Usuario atualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return this.usuarioService.atualizar(id, usuario);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deletarUsuario(@PathVariable Long id) {
        this.usuarioService.deletarPorId(id);
    }

}