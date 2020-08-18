package com.ibm.websphere.samples.batch.joboperator;

public class WaitStateException extends Exception {
    private static final long serialVersionUID = 1L;

    public WaitStateException() {
        // TODO Auto-generated constructor stub
    }

    public WaitStateException(String message) {
        super(message);
    }

    public WaitStateException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public WaitStateException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
