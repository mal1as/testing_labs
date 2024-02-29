package com.itmo.tpo.function.trigonometry;

import com.itmo.tpo.function.AbstractFunction;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SecFunction extends AbstractFunction {

    public SecFunction(double accuracy) {
        super(accuracy);
    }

    public SecFunction() {
        super();
    }

    @Override
    public BigDecimal calculate(double x) {
        BigDecimal cos = new CosFunction(this.accuracy).calculate(x);
        if (cos.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Dividing by zero in sec");
        return BigDecimal.ONE.divide(cos, 20, RoundingMode.HALF_UP);
    }
}
