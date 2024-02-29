package com.itmo.tpo.function.trigonometry;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

public class TrigonometryTest {

    private static final Double ACCURACY = pow(10, -4);

    private final CscFunction csc = new CscFunction(ACCURACY);
    private final CosFunction cos = new CosFunction(ACCURACY);
    private final CotFunction cot = new CotFunction(ACCURACY);
    private final SecFunction sec = new SecFunction(ACCURACY);
    private final SinFunction sin = new SinFunction(ACCURACY);
    private final TanFunction tan = new TanFunction(ACCURACY);


    @DisplayName("Check cos function")
    @ParameterizedTest(name = "cos({0})")
    @CsvFileSource(resources = "/trigonometry/cos.csv")
    void cosTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), cos.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check cot function")
    @ParameterizedTest(name = "cot({0})")
    @CsvFileSource(resources = "/trigonometry/cot.csv")
    void cotTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), cot.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check csc function")
    @ParameterizedTest(name = "csc({0})")
    @CsvFileSource(resources = "/trigonometry/csc.csv")
    void cscTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), csc.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check sec function")
    @ParameterizedTest(name = "sec({0})")
    @CsvFileSource(resources = "/trigonometry/sec.csv")
    void secTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), sec.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check sin function")
    @ParameterizedTest(name = "sin({0})")
    @CsvFileSource(resources = "/trigonometry/sin.csv")
    void sinTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), sin.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check tan function")
    @ParameterizedTest(name = "tan({0})")
    @CsvFileSource(resources = "/trigonometry/tan.csv")
    void tanTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), tan.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check incorrect tan function")
    @ParameterizedTest(name = "tan({0})")
    @CsvFileSource(resources = "/trigonometry/cos_eq_0.csv")
    void falseTanTest(double x) {
        assertThrows(ArithmeticException.class, () -> tan.calculate(x*Math.PI));
    }

    @DisplayName("Check incorrect sec function")
    @ParameterizedTest(name = "sec({0})")
    @CsvFileSource(resources = "/trigonometry/cos_eq_0.csv")
    void falseSecTest(double x) {
        assertThrows(ArithmeticException.class, () -> sec.calculate(x*Math.PI));
    }

    @DisplayName("Check incorrect cotan function")
    @ParameterizedTest(name = "cotan({0})")
    @CsvFileSource(resources = "/trigonometry/sin_eq_0.csv")
    void falseCotanTest(double x) {
        assertThrows(ArithmeticException.class, () -> cot.calculate(x*Math.PI));
    }

    @DisplayName("Check incorrect cosec function")
    @ParameterizedTest(name = "cosec({0})")
    @CsvFileSource(resources = "/trigonometry/sin_eq_0.csv")
    void falseCosecTest(double x) {
        assertThrows(ArithmeticException.class, () -> csc.calculate(x*Math.PI));
    }
}
