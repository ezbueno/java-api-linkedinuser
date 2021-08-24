package com.buenoezandro.linkedIn.api.services.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(Long id) {
        super((String.format("Usuário com ID: %s não encontrado!!!", id)));
    }

}