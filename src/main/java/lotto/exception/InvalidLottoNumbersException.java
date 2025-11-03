package lotto.exception;

public class InvalidLottoNumbersException extends IllegalArgumentException {
    public InvalidLottoNumbersException(String message) {
        super("[ERROR] " + message);
    }
}
