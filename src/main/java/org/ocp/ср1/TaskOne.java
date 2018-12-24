package org.ocp.ср1;

import org.ocp.Task;

public class TaskOne implements Task {

    private String task = "Task one";

    private String testLocalInnerClass = "Hello i'm local inner class";

    private class MemberInnerClass {

        void showTask() {
            System.out.println(task);
        }

        void testLocalInnerClass() {
            class LocalInnerClass {
                LocalInnerClass() {
                    System.out.println(testLocalInnerClass);
                }
            }

            new LocalInnerClass();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    public MemberInnerClass getInnerMemeberInstance() {
        return new MemberInnerClass();
    }

    @Override
    public void execute() {

//      Virtual method invocation
        Car car = new ElectricCar();
        car.powerUp();

//      instanceof
        if (car instanceof ElectricCar) {
            ((ElectricCar)car).findElectricStation();
        } else if (car instanceof HydrogenCar) {
            ((HydrogenCar)car).findHydrogenStation();
        }

        ElectricCar electricCar = new ElectricCar();
//        if (electricCar instanceof HydrogenCar) {}

        if (electricCar instanceof Turbo) {
            System.out.println("Turbo car");
        }


        //Contract for equals
        //reflexive
        car.equals(car);


//      Workings with ENUMS
        ProgrammingLanguage pl = ProgrammingLanguage.JAVA;

        System.out.format("%s %d\n", pl.name(), pl.ordinal());

        pl = ProgrammingLanguage.valueOf("PYTHON");

        System.out.format("%s %d\n", pl.name(), pl.ordinal());

//        IllegalArgumentException
//        pl = ProgrammingLanguage.valueOf("PYTHON");


//    Using enum in switch statements
        switch (pl) {
            case JS:
            case JAVA:
//            case ProgrammingLanguage.PYTHON:
            case PYTHON:
                System.out.println(pl.name()); break;
            default:
        }


        Seasons s = Seasons.FALL;

//        CREATING NESTED CLASSES

        MemberInnerClass memberInnerClass = new TaskOne().getInnerMemeberInstance();
        memberInnerClass.showTask();
        memberInnerClass.testLocalInnerClass();

        System.out.println(Seasons.FALL.ordinal());
    }
}
