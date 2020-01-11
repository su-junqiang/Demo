package com.test.rabbitMQ;

public class InvalidRoutingKeyException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidRoutingKeyException(String message) {
        super(message);
    }
}
