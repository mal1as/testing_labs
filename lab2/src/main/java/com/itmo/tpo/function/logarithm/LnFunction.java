package com.itmo.tpo.function.logarithm;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LnFunction extends AbstractFunction {

    public LnFunction(double accuracy) {
        super(accuracy);
    }

    public LnFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        if (x <= 0) {
            throw new IllegalArgumentException("x must be more than 0");
        }

        BigDecimal constant = BigDecimal.valueOf(((x - 1) * (x - 1)) / ((x + 1) * (x + 1)));

        BigDecimal result = BigDecimal.ZERO;
        BigDecimal current = BigDecimal.valueOf((x - 1) / (x + 1));
        int step = 1;

        while (current.abs().doubleValue() > this.accuracy / 2) {
            result = result.add(current);
            BigDecimal multiply = new BigDecimal(2).multiply(BigDecimal.valueOf(step));
            current = current.multiply(multiply.subtract(BigDecimal.valueOf(1))).multiply(constant)
                    .divide(multiply.add(BigDecimal.valueOf(1)), RoundingMode.HALF_UP);
            step++;
        }
        result = result.multiply(BigDecimal.valueOf(2));
        return result;
    }
}
