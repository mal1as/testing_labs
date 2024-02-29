package com.itmo.tpo.task1;

import static java.lang.Math.*;

public class CosFunction {

    private static final double DEFAULT_ACCURACY = pow(10, -3);
    private static final double MIN_ACCURACY = pow(10, -10);
    private static final double MAX_ACCURACY = pow(10, -1);

    public static double calculate(double x) {
        return calculate(x, DEFAULT_ACCURACY);
    }

    public static double calculate(double x, double accuracy) {
        if (accuracy < MIN_ACCURACY || accuracy > MAX_ACCURACY) {
            throw new IllegalArgumentException("Accuracy must be in [" + MIN_ACCURACY + ", " + MAX_ACCURACY + "]");
        }

        // cos(x) = cos(x + 2*PI)
        x = abs(x);
        while (x > 2 * PI) {
            x -= 2 * PI;
        }

        // (-1)^n * x^2n / (2n)!
        double nextElement = 1.0;
        double result = nextElement;
        int n = 1;

        while (abs(nextElement) > accuracy) {
            nextElement = pow(-1.0, n) * pow(x, 2 * n) / factorial(2 * n);
            result += nextElement;
            n++;
        }

        return round(result, decimalPlacesFromAccuracy(accuracy));
    }

    private static double round(double x, int decimalPlaces) {
        return Math.round(x * pow(10, decimalPlaces)) / pow(10, decimalPlaces);
    }

    private static int decimalPlacesFromAccuracy(double accuracy) {
        int result = 1;
        while (pow(10, -result) > accuracy) {
            result++;
        }
        return result;
    }

    private static double factorial(int n) {
        double result = 1.0;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
