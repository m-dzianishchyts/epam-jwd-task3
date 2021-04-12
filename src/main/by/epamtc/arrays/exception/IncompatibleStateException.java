package by.epamtc.arrays.exception;

public class IncompatibleStateException extends Exception {

    public IncompatibleStateException() {
    }

    public IncompatibleStateException(String message) {
        super(message);
    }

    public IncompatibleStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncompatibleStateException(Throwable cause) {
        super(cause);
    }
}
