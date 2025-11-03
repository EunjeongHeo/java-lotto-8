package lotto.view;

import lotto.domain.Lotto;

import java.util.List;

public class OutputView {

    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";

    public static void printLottoPurchased(List<Lotto> lottos) {
        System.out.println(lottos.size() + MESSAGE_PURCHASE_COUNT);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }
}
