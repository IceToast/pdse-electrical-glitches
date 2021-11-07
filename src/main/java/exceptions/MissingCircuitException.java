package exceptions;

public class MissingCircuitException extends RuntimeException{
    private static final long serialVersionUID = -5831970283099106013L;

    public MissingCircuitException(String message) {
        super(message);
    }

}
