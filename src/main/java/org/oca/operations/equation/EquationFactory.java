package org.oca.operations.equation;

import org.oca.operations.equation.operator.OperatorFactory;

public class EquationFactory {

    public static Equation<Integer> getBitwiseAnd() {
        return new IntegerEquation(OperatorFactory.getAndBitwise());
    }

    public static Equation<Integer> getBitwiseOr() {
        return new IntegerEquation(OperatorFactory.getOrBitwise());
    }

    public static Equation<Integer> getBitwiseXor() {
        return new IntegerEquation(OperatorFactory.getXorBitwise());
    }

    public static Equation<Integer> getLeftShiftSigned() {
        return new IntegerEquation(OperatorFactory.getLeftShiftUnsigned());
    }

    public static Equation<Integer> getRightShiftSigned() {
        return new IntegerEquation(OperatorFactory.getRightShiftSigned());
    }

    public static Equation<Integer> getRightShiftUnsigned() {
        return new IntegerEquation(OperatorFactory.getRightShiftUnsi());
    }
}
