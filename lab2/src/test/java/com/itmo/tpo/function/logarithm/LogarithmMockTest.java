package com.itmo.tpo.function.logarithm;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.Math.pow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class LogarithmMockTest {

    private static final Double ACCURACY = pow(10, -4);

    public static final LnFunction ln = mock(LnFunction.class);
    public static final LogFunction log2 = mock(LogFunction.class);
    public static final LogFunction log3 = mock(LogFunction.class);


    public static void initLnFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/logarithm/ln.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(ln.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initLog2FunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/logarithm/log2.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(log2.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initLog3FunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/logarithm/log3.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(log3.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        initLnFunctionMock();
        initLog2FunctionMock();
        initLog3FunctionMock();
    }

    @DisplayName("Check ln function")
    @ParameterizedTest(name = "ln({0})")
    @CsvFileSource(resources = "/logarithm/ln.csv")
    void lnTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), ln.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check log2 function")
    @ParameterizedTest(name = "log2({0})")
    @CsvFileSource(resources = "/logarithm/log2.csv")
    void log2Test(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), log2.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check log3 function")
    @ParameterizedTest(name = "log3({0})")
    @CsvFileSource(resources = "/logarithm/log3.csv")
    void log3Test(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), log3.calculate(x).doubleValue(), ACCURACY);
    }
}
