package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.InvalidPurchaseAmountException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @Test
    @DisplayName("1,000원으로 1개의 로또를 발행한다")
    void purchaseOneLottoWith1000Won() {
        List<Lotto> lottos = purchaseLottos(1000);
        assertThat(lottos).hasSize(1);
    }

    @Test
    @DisplayName("11,000원으로 11개의 로또를 발행한다")
    void purchaseTenLottosWith10000Won() {
        List<Lotto> lottos = purchaseLottos(11000);
        assertThat(lottos).hasSize(11);
    }

    @Test
    @DisplayName("발행된 로또는 6개의 번호를 가진다")
    void generatedLottoHasSixNumbers() {
        // given
        int amount = 3000;

        // when
        List<Lotto> lottos = lottoMachine.purchase(amount);

        // then
        for (Lotto lotto : lottos) {
            String[] numbers = splitLottoNumbers(lotto.toString());
            assertSixNumbers(numbers);
            assertValidRange(numbers);
        }
    }

    @Test
    @DisplayName("2,500원으로 구입 시 예외가 발생한다")
    void throwExceptionWhenAmountIs2500Won() {
        assertInvalidAmountThrows(2500);
    }

    @Test
    @DisplayName("최소 주문 금액보다 작으면 예외가 발생한다")
    void throwExceptionWhenAmountIsUnderMinAmount() {
        assertInvalidAmountThrows(500);
    }

    @Test
    @DisplayName("최대 주문 금액보다 크면 예외가 발생한다")
    void throwExceptionWhenAmountIsOverMaxAmount() {
        assertInvalidAmountThrows(100_009_000);
    }

    private String[] splitLottoNumbers(String lottoString) {
        String cleaned = lottoString.replace("[", "")
                .replace("]", "")
                .trim();
        String[] numbers = cleaned.split(",");
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i].trim();
        }
        return numbers;
    }

    private void assertSixNumbers(String[] numbers) {
        assertThat(numbers).hasSize(6);
    }

    private void assertValidRange(String[] numbers) {
        for (String num : numbers) {
            int value = Integer.parseInt(num);
            assertThat(value).isBetween(1, 45);
        }
    }

    private List<Lotto> purchaseLottos(int amount) {
        return lottoMachine.purchase(amount);
    }

    private void assertInvalidAmountThrows(int amount) {
        assertThatThrownBy(() -> purchaseLottos(amount))
                .isInstanceOf(InvalidPurchaseAmountException.class);
    }

}

