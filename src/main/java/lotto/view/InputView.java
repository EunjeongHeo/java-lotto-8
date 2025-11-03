package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.InvalidWinningNumbersException;

public class InputView {

    private static final String MESSAGE_PURCHASE_AMOUNT_INPUT = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBERS_INPUT = "당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_BONUS_NUMBER_INPUT = "보너스 번호를 입력해 주세요.";

    private static final String ERROR_NOT_NUMBER = "구입 금액은 숫자로만 입력해야 합니다.";
    private static final String ERROR_INVALID_NUMBER = "숫자만 입력해야 합니다.";


    public static int inputPurchaseAmount() {
        System.out.println(MESSAGE_PURCHASE_AMOUNT_INPUT);
        String input = Console.readLine();
        return parsePurchaseAmount(input);
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println(MESSAGE_WINNING_NUMBERS_INPUT);
        String input = Console.readLine();
        return parseNumbers(input);
    }

    public static int inputBonusNumber() {
        System.out.println(MESSAGE_BONUS_NUMBER_INPUT);
        String input = Console.readLine();
        return parseBonusNumber(input);
    }

    private static int parsePurchaseAmount(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidPurchaseAmountException(ERROR_NOT_NUMBER);
        }
    }

    private static List<Integer> parseNumbers(String input) {
        try {
            return Arrays.stream(input.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidWinningNumbersException(ERROR_INVALID_NUMBER);
        }
    }

    private static int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidWinningNumbersException(ERROR_INVALID_NUMBER);
        }
    }
}
