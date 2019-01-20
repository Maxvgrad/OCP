package org.oca;

public interface Equation<T> {

    T calculate(T firstOperand, T secondOperand);
}
