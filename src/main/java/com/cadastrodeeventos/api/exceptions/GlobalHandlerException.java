package com.cadastrodeeventos.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(EventoNaoEncontradoException.class)
    public ResponseEntity<DetalharException> exception(EventoNaoEncontradoException e) {
        DetalharException detalhe = new DetalharException(LocalDateTime.now(), e.getMessage(), "404");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhe);
    }
}
