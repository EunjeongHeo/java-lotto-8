package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.exception.InvalidPurchaseAmountException;

public class LottoMachine {

    private static final int LOTTO_PRICE = 1000;
    private static final int MIN_AMOUNT = 1000;
    private static final int MAX_AMOUNT = 100_000_000;

    private static final String ERROR_INVALID_RANGE = String.format("구입 금액은 %,d원 이상 %,d원 이하여야 합니다.",
            MIN_AMOUNT, MAX_AMOUNT);
    private static final String ERROR_NOT_DIVISIBLE_BY_UNIT = String.format("구입 금액은 %,d원 단위여야 합니다.",
            LOTTO_PRICE);

    public List<Lotto> purchase(int userPayment) {
        validatePayment(userPayment);
        int lottoCount = calculateLottoCount(userPayment);
        return generateLottos(lottoCount);
    }

    private void validatePayment(int userPayment) {
        if (userPayment < MIN_AMOUNT || userPayment > MAX_AMOUNT) {
            throw new InvalidPurchaseAmountException(ERROR_INVALID_RANGE);
        }
        if (userPayment % LOTTO_PRICE != 0) {
            throw new InvalidPurchaseAmountException(ERROR_NOT_DIVISIBLE_BY_UNIT);
        }
    }

    private int calculateLottoCount(int userPayment) {
        return userPayment / LOTTO_PRICE;
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateSingleLotto());
        }
        return lottos;
    }

    private Lotto generateSingleLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}
