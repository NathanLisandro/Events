package com.cadastrodeeventos.api.exceptions;

public class EventoNaoEncontradoException extends RuntimeException{

    public EventoNaoEncontradoException(String message) {
        super(message);
    }
}
