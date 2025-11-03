package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @DisplayName("6개 번호가 모두 일치하면 1등으로 집계된다")
    @Test
    void rankFirstIfAllNumbersMatch() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> results = result.results();

        assertThat(results.get(Rank.FIRST)).isEqualTo(1);
    }

    @DisplayName("5개 번호와 보너스 번호가 일치하면 2등으로 집계된다")
    @Test
    void rankSecondIfFiveMatchAndBonusMatch() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> results = result.results();

        assertThat(results.get(Rank.SECOND)).isEqualTo(1);
    }

    @DisplayName("5개 번호만 일치하면 3등으로 집계된다")
    @Test
    void rankThirdIfFiveMatchOnly() {
        List<Lotto> lottos = List.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> results = result.results();

        assertThat(results.get(Rank.THIRD)).isEqualTo(1);
    }

    @DisplayName("당첨 번호가 없으면 NONE으로 집계된다")
    @Test
    void rankNoneIfNoMatch() {
        List<Lotto> lottos = List.of(new Lotto(List.of(10, 20, 21, 22, 23, 24)));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> results = result.results();

        assertThat(results.getOrDefault(Rank.NONE, 0)).isEqualTo(1);
    }

    @DisplayName("여러 로또를 집계할 수 있다")
    @Test
    void multipleLottosCanBeAggregated() {
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), // FIRST
                new Lotto(List.of(1, 2, 3, 4, 5, 7)), // SECOND
                new Lotto(List.of(1, 2, 3, 4, 5, 8))  // THIRD
        );
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        LottoResult result = new LottoResult(lottos, winningNumbers);
        Map<Rank, Integer> results = result.results();

        assertThat(results.get(Rank.FIRST)).isEqualTo(1);
        assertThat(results.get(Rank.SECOND)).isEqualTo(1);
        assertThat(results.get(Rank.THIRD)).isEqualTo(1);
    }
}
