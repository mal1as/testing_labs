package com.itmo.tpo.function.logarithm;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LogFunction extends AbstractFunction {

    private static final double DEFAULT_BASE = 10;

    private final double base;

    public LogFunction(double accuracy, double base) {
        super(accuracy);
        if (base <= 0 || base == 1.0) {
            throw new IllegalArgumentException("base must be more than 0 and not equals 1");
        }
        this.base = base;
    }

    public LogFunction(double base) {
        super();
        this.base = base;
    }

    public LogFunction() {
        super();
        this.base = DEFAULT_BASE;
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal result = BigDecimal.ZERO;
        LnFunction ln = new LnFunction(this.accuracy);
        result = result.add(ln.calculate(x).divide(ln.calculate(base), RoundingMode.HALF_UP));
        return result;
    }
}
