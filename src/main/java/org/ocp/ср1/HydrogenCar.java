package org.ocp.ср1;

public class HydrogenCar extends Car {
    @Override
    public void drive() {

    }

    @Override
    public void powerUp() {
        fillHydrogen();
    }

    public void findHydrogenStation() {

    }

    private void fillHydrogen() {
        System.out.println("hydrogen");
    }
}
