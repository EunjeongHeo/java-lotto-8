package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.exception.InvalidPurchaseAmountException;

public class InputView {

    private static final String MESSAGE_PURCHASE_AMOUNT_INPUT = "구입금액을 입력해 주세요.";
    private static final String ERROR_NOT_NUMBER = "구입 금액은 숫자로만 입력해야 합니다.";

    public static int inputPurchaseAmount() {
        System.out.println(MESSAGE_PURCHASE_AMOUNT_INPUT);
        String input = Console.readLine();
        return parsePurchaseAmount(input);
    }

    private static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidPurchaseAmountException(ERROR_NOT_NUMBER);
        }
    }
}
