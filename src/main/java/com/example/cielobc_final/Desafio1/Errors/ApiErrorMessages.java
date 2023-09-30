package com.example.cielobc_final.Desafio1.Errors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class ApiErrorMessages {
    private HttpStatus status;
    private List<String> errors;

    public ApiErrorMessages(HttpStatus status, List<String> errors){
        this.status = status;
        this.errors = errors;
    }

    public ApiErrorMessages(HttpStatus status, String error){
        super();
        this.status = status;
        errors = Collections.singletonList(error);
    }
}
