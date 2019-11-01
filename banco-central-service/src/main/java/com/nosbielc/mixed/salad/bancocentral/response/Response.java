package com.nosbielc.mixed.salad.bancocentral.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Response<T>{

    private T data;
    private List<String> errors;

    public Response() {
        // Construtor Basico
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(error);
    }

}
