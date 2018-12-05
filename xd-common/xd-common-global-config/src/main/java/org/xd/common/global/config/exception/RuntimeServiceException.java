package org.xd.common.global.config.exception;

public class RuntimeServiceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1211803137666143547L;

    public RuntimeServiceException() {
        super();
    }

    public RuntimeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeServiceException(String message) {
        super(message);
    }

    public RuntimeServiceException(Throwable cause) {
        super(cause);
    }

}
