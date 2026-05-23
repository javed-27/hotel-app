package com.hotel.app.exceptions;

public class InvalidRequestException extends Throwable {
    public InvalidRequestException(String msg) {
        super(msg);
    }
}
