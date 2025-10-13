package ma.youcode.ehospital.exception;

public class TimeSlotIsNotAvailableException extends RuntimeException {
    public TimeSlotIsNotAvailableException(String message) {
        super(message);
    }
}
