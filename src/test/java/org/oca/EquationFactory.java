package org.oca;

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

    public static Equation<Integer> getLeftShiftUnsigned() {
        return new IntegerEquation(OperatorFactory.getLeftShiftUnsigned());
    }

    public static Equation<Integer> getRightShiftUnsigned() {
        return new IntegerEquation(OperatorFactory.getRightShiftUnsigned());
    }

    public static Equation<Integer> getRightShiftSigned() {
        return new IntegerEquation(OperatorFactory.getRightShiftSigned());
    }
}
