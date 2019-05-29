package com.nosbielc.mixed.salad.bancocentral.response;

public class Content<T> {

    private T objects;

    public Content() {
        // construtor vazio
    }

    public T getObjects() {
        return objects;
    }

    public void setObjects(T objects) {
        this.objects = objects;
    }
}
