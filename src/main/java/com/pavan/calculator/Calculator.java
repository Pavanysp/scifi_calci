package com.pavan.calculator;

public class Calculator {
    public double squareRoot(double x) {
        return Math.sqrt(x);
    }

    public long factorial(int n) {
        if (n == 0) return 1;
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public double naturalLog(double x) {
        return Math.log(x);
    }

    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
