package org.oca.operations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.oca.operations.equation.Equation;
import org.oca.operations.equation.EquationFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HashMapConstantsTest {


    @Test
    @DisplayName("MAXIMUM_CAPACITY = 1 << 30 = 1073741824")
    void maxCapacity() {
        Equation<Integer> eq = EquationFactory.getLeftShiftSigned();
        assertEquals(Integer.valueOf(1073741824), eq.calculate(1, 30));
    }

    @Test
    @DisplayName("hash()# (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)")
    void hash() {
        Equation<Integer> rightShiftSigned = EquationFactory.getRightShiftUnsigned();
        Object key = new Object();

        rightShiftSigned.calculate(1, 16);
        rightShiftSigned.calculate(2, 16);


        int h;
        int hash = (h = key.hashCode()) ^ (rightShiftSigned.calculate(h, 16));
        System.out.println("Hash: " + hash);
    }

    @Test
    @DisplayName("(n - 1) & hash")
    void calcIndexInTable() {
        Equation<Integer> bitwiseAnd = EquationFactory.getBitwiseAnd();

        Object element = new Object();

        Object[] table = new Object[] { new Object(), element, new Object() };

        bitwiseAnd.calculate(table.length-1, element.hashCode() >>> 16);

    }

    @Test
    void outOfMemoryError() {

        int[] i1 = null;
        int[] i2 = null;
        try {
            //i1 = new int[531562500];
            //i2 = new int[531562500];

        } catch (OutOfMemoryError er) {
            //System.out.println("Arr 1: " + Arrays.toString(i1));
            System.out.println("Arr 1: " + i1);
            System.out.println("Arr 2: " + Arrays.toString(i2));
        }
    }
}
