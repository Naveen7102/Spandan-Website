package com.spe.spandan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    public String message;

    public Message(@JsonProperty("message") String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}