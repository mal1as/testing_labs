package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TanFunction extends AbstractFunction {

    public TanFunction(double accuracy) {
        super(accuracy);
    }

    public TanFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal sin = new SinFunction(this.accuracy).calculate(x);
        BigDecimal cos = new CosFunction(this.accuracy).calculate(x);
        if (cos.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Dividing by zero in tangens");
        return sin.divide(cos, 20, RoundingMode.HALF_UP);
    }
}
