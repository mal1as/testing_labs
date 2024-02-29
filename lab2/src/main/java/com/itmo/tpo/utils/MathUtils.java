package com.itmo.tpo.utils;

import java.math.BigDecimal;

import static java.lang.Math.pow;

public class MathUtils {

    public static BigDecimal factorial(long n) {
        BigDecimal result = BigDecimal.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }
        return result;
    }

    public static double round(double x, int decimalPlaces) {
        return Math.round(x * pow(10, decimalPlaces)) / pow(10, decimalPlaces);
    }

    public static int decimalPlacesFromAccuracy(double accuracy) {
        int result = 1;
        while (pow(10, -result) > accuracy) {
            result++;
        }
        return result;
    }
}
