package com.itmo.tpo;

import com.itmo.tpo.context.SaveToCsvContext;
import com.itmo.tpo.function.logarithm.LogFunction;
import com.itmo.tpo.function.trigonometry.*;
import com.itmo.tpo.utils.MathUtils;

import java.math.BigDecimal;
import java.math.MathContext;

public class Main {

    public static void main(String[] args) {
//        CosFunction cos = new CosFunction();
//        cos.saveToCsv(SaveToCsvContext.builder()
//                .filePath("cos.csv")
//                .start(0.0)
//                .limit(2.0)
//                .step(0.1)
//                .build());
//
//        Function function = Function.builder()
//                .sin(new SinFunction())
//                .cos(new CosFunction())
//                .sec(new SecFunction())
//                .csc(new CscFunction())
//                .tan(new TanFunction())
//                .log2(new LogFunction(2))
//                .log3(new LogFunction(3))
//                .build();
//        for (double x = 2; x >=-2; x = MathUtils.round(x - 0.1, 1)) {
//            if (x == 0 || x == 1) continue;
//            BigDecimal result = function.calculate(x);
//            System.out.println(x + ", " + result.round(MathContext.DECIMAL128));
//        }
        System.out.println(1e2);
    }
}
