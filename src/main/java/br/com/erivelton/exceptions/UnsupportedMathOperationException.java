package br.com.erivelton.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String ex){
        super(ex);
    }
}
