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

        int hash = hashLikeAHashMap("YSXFJ".hashCode());

        System.out.println("Hash: " + hash);
    }

    @Test
    void rightShift() {

        Equation<Integer> rightShiftEq = EquationFactory.getRightShiftSigned();

        rightShiftEq.calculate(16, 1);
        rightShiftEq.calculate(-30000, 16);
    }

    @Test
    @DisplayName("(n - 1) & hash")
    void calcIndexInTable() {
        Equation<Integer> bitwiseAnd = EquationFactory.getBitwiseAnd();

        int hash = hashLikeAHashMap("YSXFJ".hashCode());
        int tableLength = 16; //default

        bitwiseAnd.calculate(tableLength - 1, hash);
    }

    @Test
    void tableSizeFor() {
        assertEquals(8, tableSizeFor(6));
        assertEquals(4, tableSizeFor(3));
        assertEquals(1, tableSizeFor(-3));
    }

    @Test
    @DisplayName("int n = cap - 1;\n" +
                 "n |= n >>> 1;\n" +
                 "n |= n >>> 2;\n" +
                 "n |= n >>> 4;\n" +
                 "n |= n >>> 8;\n" +
                 "n |= n >>> 16;")
    void tableSizeForInDetails() {
        int result = tableSizeForInDetails(7);

        assertEquals(8, result);
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

    //(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
    private int hashLikeAHashMap(int h) {
        Equation<Integer> rightShiftSigned = EquationFactory.getRightShiftUnsigned();
        Equation<Integer> xorEquation = EquationFactory.getBitwiseXor();

        return xorEquation.calculate(h, rightShiftSigned.calculate(h, 16));
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    private int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    private int tableSizeForInDetails(int cap) {
        Equation<Integer> rightUnSignedShiftEq = EquationFactory.getRightShiftUnsigned();
        Equation<Integer> bitwiseOrEq = EquationFactory.getBitwiseOr();

        int n = cap - 1;

        n = bitwiseOrEq.calculate(n, rightUnSignedShiftEq.calculate(n, 1));
        n = bitwiseOrEq.calculate(n, rightUnSignedShiftEq.calculate(n, 2));
        n = bitwiseOrEq.calculate(n, rightUnSignedShiftEq.calculate(n, 4));
        n = bitwiseOrEq.calculate(n, rightUnSignedShiftEq.calculate(n, 8));
        n = bitwiseOrEq.calculate(n, rightUnSignedShiftEq.calculate(n, 16));

        return (n < 0) ? 1 : n + 1;
    }
}
