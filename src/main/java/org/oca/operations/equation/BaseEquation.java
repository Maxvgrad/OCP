package org.oca.operations.equation;

import org.oca.operations.equation.operator.Operator;

public abstract class BaseEquation<T extends Number> implements Equation<T> {

    private final Operator<T> operator;

    BaseEquation(Operator<T> operator) {
        this.operator = operator;
    }

    @Override
    public T calculate(T firstOperand, T secondOperand) {
        T result = operator.apply(firstOperand, secondOperand);
        String oprOne = toBinaryString(firstOperand);
        String oprTwo = toBinaryString(secondOperand);
        String resultBinary = toBinaryString(result);

        String equation = String
                .format("%s (%s) %s %s (%s) = %s (%s)", oprOne, firstOperand, operator.getMathOperatorName(), oprTwo,
                        secondOperand, resultBinary, result);
        System.out.println(equation);
        return result;
    }

    protected abstract String toBinaryString(T operand);
}
