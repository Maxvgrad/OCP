package org.ocp.ch2;

import org.ocp.Task;

import java.util.function.BiFunction;

public class TaskTwo implements Task {
    @Override
    public void execute() throws Exception {
        System.out.println(multiply(2, 4, (a, b) -> a*b));
        System.out.println(multiply(2, 5, (a, b) -> { return a*b; }));

        new C("d");
    }

    private Integer multiply(int a, int b, BiFunction<Integer, Integer, Integer> multiplier) {
        return multiplier.apply(a, b);
    }
}

class A {   public A() {
    System.out.println("d");


} // A1
    public A(String s) {  this();  System.out.println("A :"+s);  }  // A2

    private String print() {
        return "";
    }
}
class B extends A {
    public int B(String s) {  System.out.println("B :"+s);  return 0; }

    public String print() {return "";}
}
class C extends B {
    private C(){ super();
        System.out.println("C()");}  // C1
public C(String s){  this();  System.out.println("C :"+s);  } // C2
public C(int i){} // C3
}