package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CotFunction extends AbstractFunction {

    public CotFunction(double accuracy) {
        super(accuracy);
    }

    public CotFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal cos = new CosFunction(this.accuracy).calculate(x);
        BigDecimal sin = new SinFunction(this.accuracy).calculate(x);
        if (sin.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Dividing by zero in cotan");
        return cos.divide(sin, 20, RoundingMode.HALF_UP);
    }
}
