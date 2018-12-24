package org.ocp.ch3;

import org.ocp.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.function.BiFunction;

public class TaskThree implements Task {
    @Override
    public void execute() throws Exception {
        test();
        TaskThree.<String>testStrangSyntax("String1");

        readList(new ArrayList<>());
//        readList(new ArrayList<String>());
        readUnboundedList(new ArrayList<String>());

        readUpperBoundedList(new ArrayList<>());

        Object[] objectsArray = new String[2];
        objectsArray[0] = "hello";

        System.out.println(objectsArray[0]);

        Object[] oA = new Integer[0];
//        Object[] oA2 = new int[0];

        List<? extends User> uList = new ArrayList<>();

        Admin a = new Admin();
//        uList.add(a);
//        uList.add(new Admin());
//        uList.add(new User());

        upperCaseBoundUsers(uList);


        List<String> l = new ArrayList<>();

        List<Object> l2 = new ArrayList<>(l);
//        addElement(l);
        addElement(l2);

        List<String> tables = new ArrayList<>();
        tables.add("jobs");
        tables.add("sessions");
        tables.add("commission");
        System.out.println(tables);

        tables.replaceAll(t -> t + "_log");
        System.out.println(tables);

        System.out.println(3 >> 2);
        System.out.println(3 << 1);
        System.out.println((Integer.MAX_VALUE) << 1);
        System.out.println((Integer.MIN_VALUE) << 1);

        try {

            Arrays.copyOfRange(new int[]{2, 3, 5}, -1, Integer.MAX_VALUE);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

//        int[] arr = Arrays.copyOfRange(new int[]{2, 3, 5}, -1, Integer.MAX_VALUE-6); //OUT OF MEMORY ERROR
//        System.out.println("arr size: " + arr.length);

        List list = new ArrayList();
        list.add(list);
        list.add(list);
        System.out.println(list);

        System.out.println(propperToString(list));
        //propper iteration over collection

        System.out.println(new ArrayList<>().subList(0, 0) instanceof ArrayList);
        System.out.println(new ArrayList<>().subList(0, 0) instanceof RandomAccess);


        List<String> ll = Arrays.asList("zero", "one", "two", "three", "four", "five", "six");

        List<String> subList = ll.subList(1, 5);

        System.out.println("Is it ListIterator? " + (subList.iterator() instanceof ListIterator));
        ListIterator<String> li = (ListIterator<String>) subList.iterator();

        li.next();
        li.set("instead of one");

        System.out.println(ll);

        List<String> lll = new ArrayList<>();
        lll.add("Jon");
        lll.add("Jon");
        lll.add("Jon");
        lll.add("Jon");

//        checkMethodAddPerormance(lll, (l5,v) -> {
//            long t1 = System.nanoTime();
//            l5.set(3, v);
//            return System.nanoTime() - t1;
//        }, 10000, "hello", "William", "Doe");

        reproduceBug6260652(new String[0]);
        int[] intArr = new int[]{1, 2, 3, 4, 6};

        int[] intArr2 = Arrays.copyOf(intArr, 4);

        System.out.println(intArr == intArr2);
        System.out.println(intArr.length);
        System.out.println(intArr2.length);

        System.arraycopy(intArr, 0, intArr2, 0, 4);

        Integer[] arrInteger = new Integer[]{1, 3, 4, 5, 7,};

//        arrInteger = intArr;

//        System.arraycopy(intArr, 0, arrInteger, 0, 4);

        List<String> listOne = new ArrayList<>();
        listOne.add("one");
        listOne.add("two");
        listOne.add("three");
        listOne.add("four");

        System.out.println(listOne.size());
        String[] stringsArr = listOne.toArray(new String[0]);

        System.out.println(stringsArr.length);

        stringsArr[0] = null;

        System.out.println(listOne.size());

    }


    static <T> void test() {
        System.out.println("Hello");
    }

    static <T> T testStrangSyntax(T input) {
        System.out.println(input);
        return input;
    }

    static void readList(List<Object> objects) {
        //System.out.println(objects.get(0));
        //Object o = objects.get(0);
    }

    static void readUnboundedList(List<?> objects) {
        //Object o = objects.get(0);
    }

    static void readUpperBoundedList(List<? extends IOException> exceptions) {
        for (IOException exception : exceptions) {
            exception.printStackTrace();
        }
    }

    static void upperCaseBoundUsers(List<? extends User> users) {
        users.remove(new Admin());
//        users.add(new Admin());
    }

    static void addElement(List<Object> list) {
        list.add("hey");
    }

    private static class User {}

    private static class Admin extends User {}

    private static String propperToString(Collection collection) {
        Objects.requireNonNull(collection);
        Iterator i = collection.iterator();
        StringBuilder sb = new StringBuilder();

        if (!i.hasNext()) {
            return sb.append("[]").toString();
        }

        sb.append("[");

        for (; ; ) {
            Object o = i.next();

            sb.append(o == collection ? "(this collection)" : o);

            if (!i.hasNext()) {
                return sb.append("]").toString();
            }

            sb.append(", ");

        }
    }

    private static <T, V> void checkMethodAddPerormance(T collection, BiFunction<T, V, Long> biF, int numOfIterations,
                                                        V... values) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(values);
        if (numOfIterations < 0) {
            numOfIterations = 10;
        }
        final int vSize = values.length;
        V value = null;
        for (int i = 0; i < numOfIterations; ) {
            value = values[i % vSize];
            long time = biF.apply(collection, value);
            System.out.printf("Time %d ns.,\tIter: %d, Val: %s\n",
                              time, ++i, value);
        }
    }

    private static void reproduceBug6260652(String[] args) {
        List l = Arrays.asList(args);
        System.out.println(l.toArray());
        System.out.println(l.toArray(new Object[0]));
    }
}
