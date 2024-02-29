package com.itmo.tpo.function.trigonometry;

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

public class TrigonometryMockTest {

    private static final Double ACCURACY = pow(10, -4);

    public static final CscFunction csc = mock(CscFunction.class);
    public static final CosFunction cos = mock(CosFunction.class);
    public static final CotFunction cot = mock(CotFunction.class);
    public static final SecFunction sec = mock(SecFunction.class);
    public static final SinFunction sin = mock(SinFunction.class);
    public static final TanFunction tan = mock(TanFunction.class);


    public static void initCscFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/csc.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(csc.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initCosFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/cos.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cos.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initCotFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/cot.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(cot.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initSecFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/sec.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(sec.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initSinFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/sin.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(sin.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    public static void initTanFunctionMock() throws IOException, CsvException {
        try (CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/trigonometry/tan.csv"))) {
            List<String[]> records = csvReader.readAll();
            for (String[] record : records) {
                when(tan.calculate(Double.parseDouble(record[0]))).thenReturn(BigDecimal.valueOf(Double.parseDouble(record[1])));
            }
        }
    }

    @BeforeAll
    public static void fillMocks() throws IOException, CsvException {
        initCscFunctionMock();
        initCosFunctionMock();
        initCotFunctionMock();
        initSecFunctionMock();
        initSinFunctionMock();
        initTanFunctionMock();
    }

    @DisplayName("Check csc function")
    @ParameterizedTest(name = "csc({0})")
    @CsvFileSource(resources = "/trigonometry/csc.csv")
    void cscTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), csc.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check cos function")
    @ParameterizedTest(name = "cos({0})")
    @CsvFileSource(resources = "/trigonometry/cos.csv")
    void cosTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), cos.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check cot function")
    @ParameterizedTest(name = "cot({0})")
    @CsvFileSource(resources = "/trigonometry/cot.csv")
    void cotTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), cot.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check sec function")
    @ParameterizedTest(name = "sec({0})")
    @CsvFileSource(resources = "/trigonometry/sec.csv")
    void secTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), sec.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check sin function")
    @ParameterizedTest(name = "sin({0})")
    @CsvFileSource(resources = "/trigonometry/sin.csv")
    void sinTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), sin.calculate(x).doubleValue(), ACCURACY);
    }

    @DisplayName("Check tan function")
    @ParameterizedTest(name = "tan({0})")
    @CsvFileSource(resources = "/trigonometry/tan.csv")
    void tanTest(double x, BigDecimal expected) {
        assertEquals(expected.doubleValue(), tan.calculate(x).doubleValue(), ACCURACY);
    }
}
