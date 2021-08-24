package com.buenoezandro.linkedIn.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoFone {

    COMERCIAL("Comercial"),
    CELULAR("Celular"),
    RESIDENCIAL("Residencial");

    private final String descricao;

}