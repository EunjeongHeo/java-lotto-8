package lotto;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        int amount = InputView.inputPurchaseAmount();
        List<Lotto> lottos = generateLottos(amount);
        OutputView.printLottoPurchased(lottos);

        List<Integer> winning = InputView.inputWinningNumbers();
        int bonus = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(winning, bonus);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        OutputView.printStatistics(result);
    }

    private static List<Lotto> generateLottos(int amount) {
        LottoMachine lottoMachine = new LottoMachine();
        return lottoMachine.purchase(amount);
    }
}
