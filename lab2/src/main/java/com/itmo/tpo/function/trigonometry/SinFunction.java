package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;
import com.itmo.tpo.utils.MathUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SinFunction extends AbstractFunction {

    public SinFunction(double accuracy) {
        super(accuracy);
    }

    public SinFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal result = BigDecimal.ZERO, prev;
        // todo наверное надо по-другому
        if (x % Math.PI == 0) return BigDecimal.ZERO;
        int n = 0;

        do {
            prev = result;
            result = result.add(new BigDecimal(-1).pow(n).multiply(new BigDecimal(x).pow(2 * n + 1))
                    .divide(MathUtils.factorial(2 * n + 1), 20, RoundingMode.HALF_UP));
            n++;
        } while (this.accuracy <= result.subtract(prev).abs().doubleValue());

        return result;
    }
}
