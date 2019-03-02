package org.ocp.concurrent;

import java.util.concurrent.RecursiveTask;

public class MinFinderTask extends RecursiveTask<Comparable> {

    private final Comparable[] arr;

    private final int left;
    private final int right;

    public MinFinderTask(Comparable[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Comparable compute() {
        if ((right - left) < 2) {

            return arr[left].compareTo(arr[right]) < 1 ? arr[left] : arr[right];

        } else {

            int mid = (left + right) / 2;

            MinFinderTask task = new MinFinderTask(arr, left, mid);
            task.fork();
            Comparable resultRight;
            Comparable resultLeft;

            return (resultLeft = new MinFinderTask(arr, mid, right).compute())
                    .compareTo(resultRight = task.join()) < 1 ? resultLeft : resultRight;
        }
    }
}
