package exceptions;

public class DuplicateInputChannelDetectedException extends RuntimeException {
    private static final long serialVersionUID = -7632207422147304556L;

    public DuplicateInputChannelDetectedException() {
    }

    public DuplicateInputChannelDetectedException(String message) {
        super(message);
    }
}
