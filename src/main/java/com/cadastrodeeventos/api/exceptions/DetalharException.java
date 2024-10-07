package com.cadastrodeeventos.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class DetalharException {

    private LocalDateTime dataHora;
    private String mensagemErro;
    private String codigoErro;
}
