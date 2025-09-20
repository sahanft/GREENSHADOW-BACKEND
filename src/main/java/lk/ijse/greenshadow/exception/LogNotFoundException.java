package lk.ijse.greenshadow.exception;

public class LogNotFoundException extends RuntimeException {
    public LogNotFoundException() {
    }

    public LogNotFoundException(String message) {
        super(message);
    }

    public LogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
