package com.buenoezandro.linkedIn.api.services;

import com.buenoezandro.linkedIn.api.entities.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario criar(Usuario usuario);

    List<Usuario> buscar();

    Usuario buscarPorId(Long id);

    Usuario atualizar(Long id, Usuario usuario);

    void deletarPorId(Long id);

}