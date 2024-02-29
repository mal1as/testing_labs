package com.itmo.tpo.function.logarithm;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

public class LogarithmTest {

    private static final Double ACCURACY = pow(10, -4);

    private final LnFunction ln = new LnFunction(ACCURACY);
    private final LogFunction log2 = new LogFunction(ACCURACY, 2);
    private final LogFunction log3 = new LogFunction(ACCURACY, 3);


    @DisplayName("Check ln function")
    @ParameterizedTest(name = "ln({0})")
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void lnTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), ln.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check log function with base 2")
    @ParameterizedTest(name = "log2({0})")
    @CsvFileSource(resources = "/logarithm/log2.csv")
    void log2Test(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), log2.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check log function with base 3")
    @ParameterizedTest(name = "log3({0})")
    @CsvFileSource(resources = "/logarithm/log3.csv")
    void log3Test(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), log3.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check ln function with incorrect x values")
    @ParameterizedTest(name = "ln({0})")
    @CsvFileSource(resources = "/logarithm/ln_incorrect.csv")
    void lnIncorrectXTest(double x) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> ln.calculate(x));
        assertEquals("x must be more than 0", e.getMessage());
    }

    @DisplayName("Check log function with incorrect base values")
    @ParameterizedTest(name = "log{0}")
    @CsvFileSource(resources = "/logarithm/log_incorrect_base.csv")
    void logIncorrectBaseTest(double base) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new LogFunction(ACCURACY, base));
        assertEquals("base must be more than 0 and not equals 1", e.getMessage());
    }
}
