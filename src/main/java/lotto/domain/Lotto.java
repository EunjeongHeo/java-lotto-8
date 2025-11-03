package lotto.domain;

import java.util.List;
import lotto.exception.InvalidLottoNumbersException;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String INVALID_LOTTO_NUMBER_COUNT_MESSAGE = String.format("로또 번호는 %d개여야 합니다..",
            LOTTO_NUMBER_COUNT);
    private static final String INVALID_LOTTO_DUPLICATE_MESSAGE = "로또 번호는 중복될 수 없습니다.";


    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new InvalidLottoNumbersException(INVALID_LOTTO_NUMBER_COUNT_MESSAGE);
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        long uniqueCount = numbers.stream().distinct().count();
        if (uniqueCount != numbers.size()) {
            throw new InvalidLottoNumbersException(INVALID_LOTTO_DUPLICATE_MESSAGE);
        }
    }
}
