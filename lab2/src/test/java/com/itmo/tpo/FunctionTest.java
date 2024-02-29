package com.itmo.tpo;

import com.itmo.tpo.function.logarithm.LogFunction;
import com.itmo.tpo.function.logarithm.LogarithmMockTest;
import com.itmo.tpo.function.trigonometry.*;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Function Test")
public class FunctionTest {

    private static final Double DIFF = 1e2;

    private final CscFunction csc = new CscFunction();
    private final CosFunction cos = new CosFunction();
    private final SecFunction sec = new SecFunction();
    private final SinFunction sin = new SinFunction();
    private final TanFunction tan = new TanFunction();
    private final LogFunction log2 = new LogFunction(2);
    private final LogFunction log3 = new LogFunction(3);

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv")
    void allMock(Double x, Double trueResult) throws IOException, CsvException {
        LogarithmMockTest.fillMocks();
        TrigonometryMockTest.fillMocks();
        Function function = Function.builder()
                .sin(TrigonometryMockTest.sin)
                .cos(TrigonometryMockTest.cos)
                .sec(TrigonometryMockTest.sec)
                .csc(TrigonometryMockTest.csc)
                .tan(TrigonometryMockTest.tan)
                .log2(LogarithmMockTest.log2)
                .log3(LogarithmMockTest.log3)
                .build();

        BigDecimal result = function.calculate(x);
        System.out.println(x + ", " + result.round(MathContext.DECIMAL128));
        assertEquals(trueResult, result.doubleValue(), Math.abs(trueResult) / DIFF);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv")
    void noneMock(Double x, Double trueResult) {
        Function function = Function.builder()
                .sin(sin)
                .cos(cos)
                .sec(sec)
                .csc(csc)
                .tan(tan)
                .log2(log2)
                .log3(log3)
                .build();
        BigDecimal result = function.calculate(x);
        assertEquals(trueResult, result.doubleValue(), Math.abs(trueResult) / DIFF);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv")
    void logarithmMock(Double x, Double trueResult) throws IOException, CsvException {
        LogarithmMockTest.fillMocks();
        Function function = Function.builder()
                .sin(sin)
                .cos(cos)
                .sec(sec)
                .csc(csc)
                .tan(tan)
                .log2(LogarithmMockTest.log2)
                .log3(LogarithmMockTest.log3)
                .build();

        BigDecimal result = function.calculate(x);
        assertEquals(trueResult, result.doubleValue(), Math.abs(trueResult) / DIFF);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/function.csv")
    void trigonometryMock(Double x, Double trueResult) throws IOException, CsvException {
        TrigonometryMockTest.fillMocks();
        Function function = Function.builder()
                .sin(TrigonometryMockTest.sin)
                .cos(TrigonometryMockTest.cos)
                .sec(TrigonometryMockTest.sec)
                .csc(TrigonometryMockTest.csc)
                .tan(TrigonometryMockTest.tan)
                .log2(log2)
                .log3(log3)
                .build();

        BigDecimal result = function.calculate(x);
        System.out.println(x + ", " + result.round(MathContext.DECIMAL128));
        assertEquals(trueResult, result.doubleValue(), Math.abs(trueResult) / DIFF);
    }
}