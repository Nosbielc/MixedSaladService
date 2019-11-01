package com.nosbielc.mixed.salad.bancocentral.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content<T> {

    private T objects;

    public Content() {
        // construtor vazio
    }

}
