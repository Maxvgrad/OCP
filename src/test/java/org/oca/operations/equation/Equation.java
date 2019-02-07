package org.oca.operations.equation;

public interface Equation<T> {

    T calculate(T firstOperand, T secondOperand);
}
