package com.itmo.tpo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MathUtilsTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/utils/factorial.csv")
    void factorialTest(Long n, Double expected) {
        assertEquals(expected, MathUtils.factorial(n).doubleValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/utils/round.csv")
    void roundTest(Double x, Integer decimalPlaces, Double expected) {
        assertEquals(expected, MathUtils.round(x, decimalPlaces));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/utils/decimal_places.csv")
    void decimalPlacesFromAccuracyTest(Double x, Integer expected) {
        assertEquals(expected, MathUtils.decimalPlacesFromAccuracy(x));
    }
}