package lotto.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProfitCalculator {

    private static final int SCALE = 1; // 소수점 둘째 자리에서 반올림
    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
    private static final String PROFIT_RATE_MESSAGE = "총 수익률은 %s%%입니다.";

    private final int totalPrize;
    private final int purchaseAmount;

    public ProfitCalculator(int totalPrize, int purchaseAmount) {
        this.totalPrize = totalPrize;
        this.purchaseAmount = purchaseAmount;
    }

    public BigDecimal rate() {
        if (purchaseAmount == 0) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(totalPrize)
                .divide(BigDecimal.valueOf(purchaseAmount), 10, RoundingMode.HALF_UP)
                .multiply(HUNDRED)
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    public String formattedRate() {
        return String.format(PROFIT_RATE_MESSAGE, rate());
    }
}
