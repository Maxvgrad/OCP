package org.ocp.collections;

import org.ocp.Question;

import java.util.TreeSet;

class Person {
    private static int count = 0;
    private String id = "0";
    private String interest;

    public Person(String interest) {
        this.interest = interest;
        this.id = "" + ++count;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String toString() {
        return id;
    }
}

public class TreeSetQuestion implements Question {
    private static final String MATH = "MATH";
    private TreeSet<Person> set = new TreeSet<>();

    public void add(Person p) {
        if (MATH.equals(p.getInterest())) {
            set.add(p);
        }
    }

    public TreeSet<Person> getSet() {
        return set;
    }

    @Override
    public void ask() throws Exception {
        TreeSetQuestion mathGroup = new TreeSetQuestion();
        mathGroup.add(new Person("MATH"));
        System.out.println("A");
        mathGroup.add(new Person("MATH"));
        System.out.println("B");
        System.out.println(mathGroup.set);
    }
}
