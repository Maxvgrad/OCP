package org.ocp.ср1;

public class ElectricCar extends Car {
    @Override
    public void drive() {

    }
    
    /**
     * Virtual method invocation
     */
    @Override
    public void powerUp() {
        charge();
    }

    public void findElectricStation() {

    }

    private void charge() {
        System.out.println("Charging");
    }
}
