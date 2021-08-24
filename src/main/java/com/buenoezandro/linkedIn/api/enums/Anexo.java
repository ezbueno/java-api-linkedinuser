package com.buenoezandro.linkedIn.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public enum Anexo {

    IMAGEM("Imagem"),
    VIDEO("Vídeo"),
    DOCUMENTO("Documento");

    private final String descricao;

}