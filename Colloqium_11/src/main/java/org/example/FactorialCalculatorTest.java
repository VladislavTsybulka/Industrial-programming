package org.example;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

public class FactorialCalculatorTest {

    @Test
    public void testCalculateFactorials() {
        FactorialCalculator calculator = new FactorialCalculator();

        int n1 = 5;
        List<BigInteger> expectedFactorials1 = List.of(
                BigInteger.ONE,
                BigInteger.valueOf(2),
                BigInteger.valueOf(6),
                BigInteger.valueOf(24),
                BigInteger.valueOf(120)
        );
        List<BigInteger> actualFactorials1 = calculator.calculateFactorials(n1);
        Assertions.assertEquals(expectedFactorials1, actualFactorials1);

        int n2 = 10;
        List<BigInteger> expectedFactorials2 = List.of(
                BigInteger.ONE,
                BigInteger.valueOf(2),
                BigInteger.valueOf(6),
                BigInteger.valueOf(24),
                BigInteger.valueOf(120),
                BigInteger.valueOf(720),
                BigInteger.valueOf(5040),
                BigInteger.valueOf(40320),
                BigInteger.valueOf(362880),
                BigInteger.valueOf(3628800)
        );
        List<BigInteger> actualFactorials2 = calculator.calculateFactorials(n2);
        Assertions.assertEquals(expectedFactorials2, actualFactorials2);
    }

    @Test
    public void testCalculateFactorialsNegativeInteger() {
        FactorialCalculator calculator = new FactorialCalculator();

        int negativeN = -3;
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateFactorials(negativeN);
        });
    }
}

