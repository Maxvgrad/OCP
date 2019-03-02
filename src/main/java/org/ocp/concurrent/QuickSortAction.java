package org.ocp.concurrent;

import java.util.List;
import java.util.concurrent.RecursiveAction;


//TODO: not done!
public class QuickSortAction<T extends Comparable> extends RecursiveAction {

    private final List<T> data;

    private final int startInclusive;
    private final int endExclusive;

    public QuickSortAction(List<T> list, int startInclusive, int endExclusive) {
        this.data = list;
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
    }

    @Override
    protected void compute() {

        if (endExclusive - startInclusive < 2 || startInclusive < 0 || endExclusive <= startInclusive) {
            System.out.println("start: " + startInclusive);
            System.out.println("end: " + endExclusive);

            return;
        } else {

            int pivotIndex = partition(data, startInclusive, endExclusive);

            QuickSortAction actionLeft = new QuickSortAction<>(data, startInclusive, pivotIndex);
            QuickSortAction actionRight = new QuickSortAction<>(data, pivotIndex, endExclusive);

            invokeAll(actionLeft, actionRight);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(List<T> data, final int startInclusive, final int endExclusive) {

        System.out.println("Partition: " + startInclusive + ", " + (endExclusive-1));

        int pivotIndex = (startInclusive + endExclusive) / 2;
        T pivot = data.get(pivotIndex); // mid point from data

        System.out.println("Pivot:" + pivot);

        Object[] tmpArr = new Object[endExclusive - startInclusive];
        int leftCursor = 0;
        int rightCursor = tmpArr.length-1;


        for (int i = startInclusive; i < endExclusive; i ++) {
            T elem = data.get(i);

            if (elem.compareTo(pivot) > 0) {
                //System.out.println("Right cur: " + rightCursor);
                tmpArr[rightCursor--] = elem;
            } else {
                //System.out.println("Left cur: " + leftCursor);
                tmpArr[leftCursor++] = elem;
            }
        }

        for (int i = startInclusive; i < endExclusive; i++) {
            data.set(i, (T) tmpArr[i - startInclusive]);
        }

        System.out.println("Pivot index: " + pivotIndex);
        System.out.println(data);


        return leftCursor + startInclusive - 1;
    }
}
