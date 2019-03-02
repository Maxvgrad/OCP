package org.ocp.concurrent;

import java.util.concurrent.RecursiveAction;

// Invalid ch7q12
public class CounterAction extends RecursiveAction {

    private final int start;
    private final int end;

    public CounterAction(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (start < 1) {
            return;
        } else {

            int mid = (start + end) / 2;
            invokeAll(new CounterAction(start, mid), new CounterAction(mid, end));
        }
    }
}
