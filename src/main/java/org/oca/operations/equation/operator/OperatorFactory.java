package org.oca.operations.equation.operator;

public class OperatorFactory {

    private static final Operator<Integer> AND_BITWISE = Operator.of((a, b) -> a & b, "&");
    private static final Operator<Integer> OR_BITWISE = Operator.of((a, b) -> a | b, "|");
    private static final Operator<Integer> XOR_BITWISE = Operator.of((a, b) -> a ^ b, "^");

    private static final Operator<Integer> SHIFT_LEFT_UNSIGNED = Operator.of((a, b) -> a << b, "<<");
    private static final Operator<Integer> SHIFT_RIGHT_UNSIGNED = Operator.of((a, b) -> a >> b, ">>");

    public static  Operator<Integer> getAndBitwise() {
        return AND_BITWISE;
    }

    public static  Operator<Integer> getOrBitwise() {
        return OR_BITWISE;
    }

    public static  Operator<Integer> getXorBitwise() {
        return XOR_BITWISE;
    }

    public static  Operator<Integer> getLeftShiftUnsigned() {
        return SHIFT_LEFT_UNSIGNED;
    }

    public static  Operator<Integer> getRightShiftSigned() {
        return SHIFT_RIGHT_UNSIGNED;
    }

    public static  Operator<Integer> getRightShiftUnsi() {
        return Operator.of((a, b) -> a >>> b, ">>>");
    }
}
