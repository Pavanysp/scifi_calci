package com.pavan.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    public void testSquareRoot() {
        assertEquals(5.0, calc.squareRoot(25), 0.001);
        assertEquals(3.162, calc.squareRoot(10), 0.001);
    }

    @Test
    public void testFactorial() {
        assertEquals(1, calc.factorial(0));
        assertEquals(6, calc.factorial(3));
    }

    @Test
    public void testNaturalLog() {
        assertEquals(0.0, calc.naturalLog(1), 0.001);
        assertEquals(2.303, calc.naturalLog(10), 0.001);
    }

    @Test
    public void testPower() {
        assertEquals(1.0, calc.power(5, 0), 0.001);
        assertEquals(27.0, calc.power(3, 3), 0.001);
    }
}
