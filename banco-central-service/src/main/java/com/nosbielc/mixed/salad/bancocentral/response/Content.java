package com.nosbielc.mixed.salad.bancocentral.response;

public class Content<T> {

    private T content;

    public Content() {
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
