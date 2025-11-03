package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.ProfitCalculator;
import lotto.domain.WinningNumbers;
import lotto.exception.InvalidPurchaseAmountException;
import lotto.exception.InvalidWinningNumbersException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        int amount = readPurchaseAmount();
        List<Lotto> lottos = generateLottos(amount);
        OutputView.printLottoPurchased(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        LottoResult result = new LottoResult(lottos, winningNumbers);
        OutputView.printStatistics(result);

        ProfitCalculator calculator = new ProfitCalculator(result.totalPrize(), amount);
        OutputView.printProfitRate(calculator);
    }

    private static int readPurchaseAmount() {
        try {
            return InputView.inputPurchaseAmount();
        } catch (InvalidPurchaseAmountException e) {
            System.out.println(e.getMessage());
            return readPurchaseAmount();
        }
    }

    private static WinningNumbers readWinningNumbers() {
        try {
            List<Integer> winning = InputView.inputWinningNumbers();
            int bonus = InputView.inputBonusNumber();
            return new WinningNumbers(winning, bonus);
        } catch (InvalidWinningNumbersException e) {
            System.out.println(e.getMessage());
            return readWinningNumbers();
        }
    }

    private static List<Lotto> generateLottos(int amount) {
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.purchase(amount);
    }
}
