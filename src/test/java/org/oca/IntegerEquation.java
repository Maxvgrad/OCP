package org.oca;

public class IntegerEquation extends BaseEquation<Integer> {

    public IntegerEquation(Operator<Integer> operator) {
        super(operator);
    }

    @Override
    protected String toBinaryString(Integer operand) {
        return Integer.toBinaryString(operand);
    }
}
