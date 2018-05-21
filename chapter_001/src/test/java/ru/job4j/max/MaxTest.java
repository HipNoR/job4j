package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since $ID$
 * @version 0.1
 */
public class MaxTest {
    @Test
    public void thenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
}
