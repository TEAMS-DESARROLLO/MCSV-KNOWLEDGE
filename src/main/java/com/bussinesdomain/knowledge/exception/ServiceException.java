package com.bussinesdomain.knowledge.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceException extends RuntimeException {

    private Throwable throwable;

    public ServiceException(String message, Throwable e) {

        super(message);
        this.throwable = e;
    }
    
}
