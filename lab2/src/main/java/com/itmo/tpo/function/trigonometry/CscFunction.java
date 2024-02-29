package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CscFunction extends AbstractFunction {

    public CscFunction(double accuracy) {
        super(accuracy);
    }

    public CscFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal sin = new SinFunction(this.accuracy).calculate(x);
        if (sin.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Dividing by zero in cosec");
        return BigDecimal.ONE.divide(sin, 20, RoundingMode.HALF_UP);
    }
}
