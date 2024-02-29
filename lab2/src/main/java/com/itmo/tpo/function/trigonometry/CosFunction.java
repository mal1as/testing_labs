package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.lang.Math.*;

public class CosFunction extends AbstractFunction {

    public CosFunction(double accuracy) {
        super(accuracy);
    }

    public CosFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal result = BigDecimal.ONE;
        result = result.subtract(new SinFunction(this.accuracy).calculate(x).pow(2)).sqrt(MathContext.DECIMAL128);
        x = abs(x % (2 * PI));
        return x > PI / 2 && x < 3 * PI / 2 ? result.multiply(BigDecimal.valueOf(-1)) : result;
    }
}
