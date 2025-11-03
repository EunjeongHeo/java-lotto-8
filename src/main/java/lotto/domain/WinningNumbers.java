package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.exception.InvalidWinningNumbersException;

public class WinningNumbers {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final String ERROR_BONUS_DUPLICATE = "보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    private static final String ERROR_NUMBER_OUT_OF_RANGE = String.format("로또 번호는 %d부터 %d 사이의 숫자여야 합니다.",
            MIN_NUMBER, MAX_NUMBER);

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        validateRange(numbers);
        validateBonusDuplicate(numbers, bonusNumber);
        validateRange(List.of(bonusNumber));

        this.winningLotto = new Lotto(numbers);
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        int matchCount = (int) lotto.getNumbers().stream()
                .filter(this::contains)
                .count();

        boolean bonusMatched = containsBonus(lotto);
        return Rank.of(matchCount, bonusMatched);
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_NUMBER || number > MAX_NUMBER) {
                throw new InvalidWinningNumbersException(ERROR_NUMBER_OUT_OF_RANGE);
            }
        }
    }

    private void validateBonusDuplicate(List<Integer> numbers, int bonusNumber) {
        Set<Integer> numberSet = new HashSet<>(numbers);
        if (numberSet.contains(bonusNumber)) {
            throw new InvalidWinningNumbersException(ERROR_BONUS_DUPLICATE);
        }
    }


    private boolean contains(int number) {
        return winningLotto.getNumbers().contains(number);
    }

    private boolean containsBonus(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }
}
