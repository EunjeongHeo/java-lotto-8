package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.InvalidLottoNumbersException;
import lotto.exception.InvalidWinningNumbersException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("정상적인 당첨 번호와 보너스 번호를 입력하면 객체가 생성된다")
    @Test
    void createWinningNumbersWithValidNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        assertThatCode(() -> new WinningNumbers(numbers, bonusNumber))
                .doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다")
    @Test
    void throwExceptionWhenWinningNumbersAreDuplicated() {
        List<Integer> numbers = List.of(1, 2, 2, 4, 5, 6);
        int bonusNumber = 7;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(InvalidLottoNumbersException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다")
    @Test
    void throwExceptionWhenBonusNumberIsDuplicatedWithWinningNumbers() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 6;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(InvalidWinningNumbersException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("당첨 번호 중 1~45 범위를 벗어나면 예외가 발생한다")
    @Test
    void throwExceptionWhenWinningNumbersAreOutOfRange() {
        List<Integer> numbers = List.of(0, 2, 3, 4, 5, 6);
        int bonusNumber = 7;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(InvalidWinningNumbersException.class)
                .hasMessageContaining("[ERROR]");
    }

    @DisplayName("보너스 번호가 1~45 범위를 벗어나면 예외가 발생한다")
    @Test
    void throwExceptionWhenBonusNumberIsOutOfRange() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        int bonusNumber = 46;

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonusNumber))
                .isInstanceOf(InvalidWinningNumbersException.class)
                .hasMessageContaining("[ERROR]");
    }
}
