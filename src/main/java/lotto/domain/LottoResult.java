package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {

    private final Map<Rank, Integer> results = new EnumMap<>(Rank.class);

    public LottoResult(List<Lotto> lottos, WinningNumbers winningNumbers) {
        calculateResults(lottos, winningNumbers);
    }

    private void calculateResults(List<Lotto> lottos, WinningNumbers winningNumbers) {
        for (Lotto lotto : lottos) {
            Rank rank = winningNumbers.match(lotto);
            results.put(rank, results.getOrDefault(rank, 0) + 1);
        }
    }

    public Map<Rank, Integer> results() {
        return results;
    }

    public int totalPrize() {
        return results.entrySet().stream()
                .mapToInt(entry -> entry.getKey().prize() * entry.getValue())
                .sum();
    }
}
