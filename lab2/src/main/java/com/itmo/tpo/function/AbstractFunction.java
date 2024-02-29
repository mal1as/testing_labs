package com.itmo.tpo.function;

import com.itmo.tpo.context.SaveToCsvContext;
import com.itmo.tpo.utils.MathUtils;
import lombok.SneakyThrows;

import java.io.FileWriter;
import java.math.BigDecimal;

import static java.lang.Math.*;

public abstract class AbstractFunction {

    private static final double MIN_ACCURACY = pow(10, -10);
    private static final double MAX_ACCURACY = pow(10, -1);

    protected final double accuracy;

    public AbstractFunction(double accuracy) {
        if (accuracy < MIN_ACCURACY || accuracy > MAX_ACCURACY) {
            throw new IllegalArgumentException("Accuracy must be in [" + MIN_ACCURACY + ", " + MAX_ACCURACY + "]");
        }
        this.accuracy = accuracy;
    }

    public AbstractFunction() {
        this(MIN_ACCURACY);
    }

    public abstract BigDecimal calculate(double x);

    @SneakyThrows
    public void saveToCsv(SaveToCsvContext ctx) {
        int decimalPlaces = MathUtils.decimalPlacesFromAccuracy(this.accuracy);
        ctx.setDecimalPlaces(decimalPlaces);

        try (FileWriter writer = new FileWriter(ctx.getFilePath(), false)) {
            writer.write(toCsv(ctx));
            writer.flush();
        }
    }

    private String toCsv(SaveToCsvContext ctx) {
        StringBuilder result = new StringBuilder();
        double x = ctx.getStart();

        while (x <= ctx.getLimit()) {
            result.append(x)
                    .append(ctx.getDelimiter())
                    .append(MathUtils.round(calculate(x).doubleValue(), ctx.getDecimalPlaces()))
                    .append("\n");
            x += ctx.getStep();
        }
        return result.toString();
    }
}
