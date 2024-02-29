package com.itmo.tpo;

import com.itmo.tpo.function.logarithm.LogFunction;
import com.itmo.tpo.function.trigonometry.*;
import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Builder
public class Function {

    @NonNull
    private SinFunction sin;
    @NonNull
    private CosFunction cos;
    @NonNull
    private SecFunction sec;
    @NonNull
    private CscFunction csc;
    @NonNull
    private TanFunction tan;
    @NonNull
    private LogFunction log2;
    @NonNull
    private LogFunction log3;


    public BigDecimal calculate(double x) {
        if (x > 0) {
            BigDecimal log2x = log2.calculate(x);
            BigDecimal log3x = log3.calculate(x);
            return log2x.multiply(log3x)
                    .multiply(log2x)
                    .divide(log3x, 20, RoundingMode.HALF_UP)
                    .add(log2x)
                    .divide(log2x.subtract(log3x), 20, RoundingMode.HALF_UP);
        }

        BigDecimal sinX = sin.calculate(x);
        BigDecimal cosX = cos.calculate(x);
        BigDecimal secX = sec.calculate(x);
        BigDecimal cscX = csc.calculate(x);
        BigDecimal tanX = tan.calculate(x);
        return (cosX.add(tanX))
                .multiply(secX)
                .multiply(secX)
                .add(sinX)
                .pow(3)
                .subtract(tanX)
                .multiply(cscX.divide(cosX, 20, RoundingMode.HALF_UP)
                        .pow(2)
                        .divide(cscX, 20, RoundingMode.HALF_UP)
                        .pow(2)
                        .pow(2));
    }
}
