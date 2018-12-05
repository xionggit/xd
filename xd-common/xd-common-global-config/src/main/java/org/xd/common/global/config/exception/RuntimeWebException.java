package org.xd.common.global.config.exception;

public class RuntimeWebException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1326924771164341468L;

    public RuntimeWebException() {
        super();
    }

    public RuntimeWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeWebException(String message) {
        super(message);
    }

    public RuntimeWebException(Throwable cause) {
        super(cause);
    }

}
