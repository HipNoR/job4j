package ru.job4j.converter;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    private final static MathContext CONTEXT = new MathContext(2, RoundingMode.HALF_EVEN);

    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        BigDecimal result = converter.rubleToDollar(new BigDecimal(60.00, CONTEXT));
        BigDecimal expected = new BigDecimal(1.00, CONTEXT);
        assertThat(result, is(expected));
    }

    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        BigDecimal result = converter.rubleToEuro(new BigDecimal(70.00, CONTEXT));
        BigDecimal expected = new BigDecimal(1.00, CONTEXT);
        assertThat(result, is(expected));
    }

    @Test
    public void when1DollarToRubleThen60() {
        Converter converter = new Converter();
        BigDecimal result = converter.dollarToRuble(new BigDecimal(1.00, CONTEXT));
        BigDecimal expected = new BigDecimal(60.00, CONTEXT);
        assertThat(result, is(expected));
    }

    @Test
    public void when1EuroToRubleThen70() {
        Converter converter = new Converter();
        BigDecimal result = converter.euroToRuble(new BigDecimal(1.00, CONTEXT));
        BigDecimal expected = new BigDecimal(70.00, CONTEXT);
        assertThat(result, is(expected));
    }
}
