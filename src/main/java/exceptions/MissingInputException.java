package exceptions;

public class MissingInputException extends RuntimeException{
    private static final long serialVersionUID = 6397217998707865963L;

    public MissingInputException(String message) {
        super(message);
    }

    public MissingInputException() {

    }
}
