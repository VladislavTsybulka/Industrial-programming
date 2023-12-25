package org.example;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorialCalculator {

    public List<BigInteger> calculateFactorials(int N) throws IllegalArgumentException {
        if (N < 0) {
            throw new IllegalArgumentException("N should be a positive number.");
        }

        List<BigInteger> factorials = new ArrayList<>();
        BigInteger factorial = BigInteger.ONE;

        for (int i = 1; i <= N; i++) {
            factorial = factorial.multiply(BigInteger.valueOf(i));
            factorials.add(factorial);
        }

        return factorials;
    }
}