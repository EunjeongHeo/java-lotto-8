package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;

public class OutputView {

    private static final String MESSAGE_PURCHASE_COUNT = "개를 구매했습니다.";
    private static final String MESSAGE_STATISTICS_HEADER = "당첨 통계\n---";

    public static void printLottoPurchased(List<Lotto> lottos) {
        System.out.println();
        System.out.println(lottos.size() + MESSAGE_PURCHASE_COUNT);
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.println();
    }

    public static void printStatistics(LottoResult result) {
        System.out.println();
        System.out.println(MESSAGE_STATISTICS_HEADER);

        Map<Rank, Integer> results = result.results();

        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .sorted((r1, r2) -> Integer.compare(r1.prize(), r2.prize()))
                .forEach(rank -> {
                    int count = results.getOrDefault(rank, 0);
                    System.out.println(rank.message() + " - " + count + "개");
                });
    }
}
