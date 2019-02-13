package ru.job4j.converter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Class Converter - currency converter.
 * Convert RUB to USD, RUB to EUR, USD to RUB, EUR to RUB.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Converter {
    private final MathContext context = new MathContext(2, RoundingMode.HALF_EVEN);
    private final BigDecimal eur = new BigDecimal(70.00, this.context);
    private final BigDecimal usd = new BigDecimal(60.00, this.context);

    /**
     * Convert RUB to EUR.
     * @param value RUB.
     * @return EUR.
     */
    public BigDecimal rubleToEuro(BigDecimal value) {
        return value.divide(eur, this.context);
    }

    /**
     * Convert RUB to USD.
     * @param value RUB.
     * @return USD
     */
    public BigDecimal rubleToDollar(BigDecimal value) {
        return value.divide(usd, this.context);
    }

    /**
     * Convert EUR to RUB.
     * @param value EUR.
     * @return RUB.
     */
    public BigDecimal euroToRuble(BigDecimal value) {
        return value.multiply(eur, this.context);
    }

    /**
     * Convert USD to RUB.
     * @param value USD.
     * @return RUB
     */
    public BigDecimal dollarToRuble(BigDecimal value) {
        return value.multiply(usd, this.context);    }
}
