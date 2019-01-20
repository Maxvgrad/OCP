package org.oca.operations;

import org.junit.jupiter.api.Test;
import org.oca.Equation;
import org.oca.EquationFactory;

class BitWiseOperatorsTest {

    @Test
    void bitwiseAnd() {
        Equation<Integer> eq = EquationFactory.getBitwiseAnd();
        eq.calculate(3, 4);
        eq.calculate(1, 6);
        eq.calculate(7, 1);
        eq.calculate(2019, 3);
    }

    @Test
    void bitwiseOr() {
        Equation<Integer> eq = EquationFactory.getBitwiseOr();
        eq.calculate(3, 4);
        eq.calculate(1, 6);
        eq.calculate(7, 1);
    }

    @Test
    void bitwiseXor() {
        Equation<Integer> eq = EquationFactory.getBitwiseXor();
        eq.calculate(3, 4);
        eq.calculate(1, 6);
        eq.calculate(7, 1);
        eq.calculate(Integer.MIN_VALUE, 2018);
    }

    @Test
    void leftShiftUnsigned() {
        Equation<Integer> eq = EquationFactory.getLeftShiftUnsigned();
        eq.calculate(3, 4);
        eq.calculate(1, 6);
        eq.calculate(7, 1);
        eq.calculate(Integer.MIN_VALUE, 1);
        eq.calculate(Integer.MIN_VALUE, 2018);
    }

    @Test
    void rightShiftUnsigned() {
        Equation<Integer> eq = EquationFactory.getRightShiftUnsigned();
        eq.calculate(3, 4);
        eq.calculate(1, 6);
        eq.calculate(7, 1);
        eq.calculate(Integer.MIN_VALUE, 4);
    }

    @Test
    void rightShiftSigned() {
        Equation<Integer> eq = EquationFactory.getRightShiftSigned();
        eq.calculate(3, 1);
        eq.calculate(2, 6);
        eq.calculate(1, 4);
        eq.calculate(0, 4);
        eq.calculate(-1, 1);
        eq.calculate(-2, 1);
        eq.calculate(-3, 1);

        eq.calculate(Integer.MIN_VALUE, 1);
        eq.calculate(Integer.MIN_VALUE/2, 1);
        eq.calculate(Integer.MIN_VALUE/4, 1);
        eq.calculate(Integer.MIN_VALUE/8, 1);
        eq.calculate(Integer.MIN_VALUE/16, 1);
        eq.calculate(Integer.MIN_VALUE/32, 1);
        eq.calculate(Integer.MIN_VALUE/64, 1);
        eq.calculate(Integer.MIN_VALUE/128, 1);
        eq.calculate(Integer.MIN_VALUE/256, 1);
        eq.calculate(Integer.MIN_VALUE/512, 1);
        eq.calculate(Integer.MIN_VALUE/1024, 1);
        eq.calculate(Integer.MAX_VALUE, 4);
        eq.calculate(Integer.MAX_VALUE/2, 4);
        eq.calculate(Integer.MAX_VALUE/4, 4);
        eq.calculate(Integer.MAX_VALUE/8, 4);
        eq.calculate(Integer.MAX_VALUE/16, 4);
        eq.calculate(Integer.MAX_VALUE/32, 4);
        eq.calculate(Integer.MAX_VALUE/64, 4);
        eq.calculate(Integer.MAX_VALUE/128, 4);
        eq.calculate(Integer.MAX_VALUE/256, 4);
        eq.calculate(Integer.MAX_VALUE/512, 4);
        eq.calculate(Integer.MAX_VALUE/1024, 4);
    }
}

