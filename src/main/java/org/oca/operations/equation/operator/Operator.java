package org.oca.operations.equation.operator;

import java.util.function.BinaryOperator;

public class Operator<T extends Number> {

    private final BinaryOperator<T> operator;

    private final String mathOperatorName;

    private Operator(BinaryOperator<T> operator, String mathOperatorName) {
        this.operator = operator;
        this.mathOperatorName = mathOperatorName;
    }

    public static <T extends Number> Operator<T> of(BinaryOperator<T> operator, String mathOperatorName) {
        return new Operator<>(operator, mathOperatorName);
    }

    public String getMathOperatorName() {
        return mathOperatorName;
    }

    public T apply(T a, T b) {
        return operator.apply(a, b);
    }


}
