package org.ocp.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ForkJoinPoolTest {

    private List<Integer> list;

    private ForkJoinPool forkJoinPool;

    @BeforeEach
    void setUp() {
        forkJoinPool = new ForkJoinPool();
        list = Stream.of(1, 3, 3, 2, 5, 4, 99, 6).collect(Collectors.toList());
    }

    @Test
    void quickSort() throws Exception {
        list = new ArrayList<>(list);

        list = Collections.synchronizedList(list);

        forkJoinPool.invoke(new QuickSortAction<>(list, 0, list.size()));

        forkJoinPool.shutdown();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(list);
    }

    @Test
    void findMin() {
        list.add(Integer.MIN_VALUE);

        Comparable min = forkJoinPool.invoke(new MinFinderTask(list.toArray(new Comparable[list.size()]), 0, list.size() - 1));

        assertEquals(0, min.compareTo(Integer.MIN_VALUE));
    }

    @Test
    void commonLogic() {

        new RecursiveAction() {
            private Object[] dataSet;

            @Override
            protected void compute() {
                if (isSmallEnough(dataSet)) {
                    processDirectly(dataSet);  //LINE 10
                } else {
                    List<Object> list = splitDataSet(dataSet); //LINE 13
                    List<RecursiveAction> subactions = Collections.emptyList(); //build MyRecursiveAction objects for each DataSe
                    invokeAll(subactions); //LINE 15   }  }
                }
            }

            private boolean isSmallEnough(Object[] dataSet) {
                return true;
            }

            private void processDirectly(Object[] dataSet) {

            }

            private List<Object> splitDataSet(Object[] dataSet) {
                return Collections.emptyList();
            }
        };
    }


}
