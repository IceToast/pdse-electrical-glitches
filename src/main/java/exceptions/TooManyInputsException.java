package exceptions;

public class TooManyInputsException extends RuntimeException {
    private static final long serialVersionUID = 61637881287801682L;

    public TooManyInputsException(String message) {
        super(message);
    }
}
