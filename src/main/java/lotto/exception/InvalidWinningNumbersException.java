package lotto.exception;

public class InvalidWinningNumbersException extends IllegalArgumentException {
    public InvalidWinningNumbersException(String message) {
        super("[ERROR] " + message);
    }
}
