package com.itmo.tpo.task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

class CosFunctionTest {

    @DisplayName("Check PI values")
    @ParameterizedTest(name = "cos({0})")
    @ValueSource(doubles = {-2 * PI, -1.5 * PI, -PI, -0.5 * PI, 0,
            0.5 * PI, PI, 1.5 * PI, 2 * PI})
    public void checkPiValues(double val) {
        assertAll(
                () -> assertEquals(cos(val), CosFunction.calculate(val, 0.0001), 0.0001)
        );
    }

    @DisplayName("Check cos(n) = cos(n + 2*PI)")
    @ParameterizedTest(name = "cos({0}) = cos({0} + 2*PI)")
    @ValueSource(doubles = {0.7893, -1.2345, 3.1416, -2.7183, 0.0457, -0.9091, 4.5678, -2.2222, 0.9876, -3.3333})
    public void checkCyclicValues(double val) {
        assertAll(
                () -> assertEquals(CosFunction.calculate(val, 0.0001),
                        CosFunction.calculate(val + 2 * PI, 0.0001), 0.0001)
        );
    }

    @DisplayName("Check cos(n) = cos(n + 2*PI)")
    @ParameterizedTest(name = "cos({0}) = cos(-{0})")
    @ValueSource(doubles = {-1.2345, 3.1416, -2.7183, 0.0457, -0.7893, 0.9091, -4.5678, 2.2222, -0.9876, 3.3333})
    public void checkOppositeValues(double val) {
        assertAll(
                () -> assertEquals(CosFunction.calculate(val, 0.0001),
                        CosFunction.calculate(-val, 0.0001), 0.0001)
        );
    }

    @DisplayName("Check table values")
    @ParameterizedTest(name = "cos({0}) = {1}")
    @CsvFileSource(resources = "/table_values.csv", delimiter = ';')
    public void checkTableValues(double input, double expected) {
        assertAll(
                () -> assertEquals(expected, CosFunction.calculate(input), 0.001)
        );
    }

    @DisplayName("Check incorrect accuracy")
    @Test
    public void checkIncorrectAccuracy() {
        assertThrows(IllegalArgumentException.class, () -> CosFunction.calculate(1, 0.11));
        assertThrows(IllegalArgumentException.class, () -> CosFunction.calculate(1, 0.00000000009));
    }
}