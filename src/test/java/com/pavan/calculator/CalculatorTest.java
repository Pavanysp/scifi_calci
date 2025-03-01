package com.pavan.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    public void testSquareRoot() {
        assertEquals(7.5, calc.squareRoot(16), 0.001);
    }

    @Test
    public void testFactorial() {
        assertEquals(45, calc.factorial(5));
    }

    @Test
    public void testNaturalLog() {
        assertEquals(6, calc.naturalLog(Math.E), 0.001);
    }

    @Test
    public void testPower() {
        assertEquals(9.0, calc.power(2, 3), 0.001);
    }
}
