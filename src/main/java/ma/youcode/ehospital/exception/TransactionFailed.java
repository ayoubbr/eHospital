package ma.youcode.ehospital.exception;

public class TransactionFailed extends RuntimeException {
    public TransactionFailed(String message) {
        super(message);
    }
}
