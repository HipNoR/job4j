package ru.job4j.converter;

/**
 * Class Converter - currency converter.
 * Convert RUB to USD, RUB to EUR, USD to RUB, EUR to RUB.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class Converter {

    /**
     * Convert RUB to EUR.
     * @param value RUB.
     * @return EUR.
     */
    public int rubleToEuro(int value) {
        return value / 70;
    }

    /**
     * Convert RUB to USD.
     * @param value RUB.
     * @return USD
     */
    public int rubleToDollar(int value) {
        return value / 60;
    }

    /**
     * Convert EUR to RUB.
     * @param value EUR.
     * @return RUB.
     */
    public int euroToRuble(int value) {
        return value * 70;
    }

    /**
     * Convert USD to RUB.
     * @param value USD.
     * @return RUB
     */
    public int dollarToRuble(int value) {
        return value * 60;
    }
}
