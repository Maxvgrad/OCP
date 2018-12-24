package org.ocp.ср1;

public enum Seasons {
    SUMMER("summer") {
        @Override
        public void averageTemperature() {
            System.out.println("+30");
        }
    }, FALL("fall") {
        @Override
        public void averageTemperature() {
            System.out.println("+4");
        }
    }, WINTER("winter") {
        @Override
        public void averageTemperature() {
            System.out.println("-10");
        }
    }, SPRING("spring") {
        @Override
        public void averageTemperature() {
            System.out.println("+7");
        }

        @Override
        public void showRegion() {
            System.out.println("Provance");
        }
    };

    Seasons(String name) {
        System.out.format("Hello %s", name);
    }

    public abstract void averageTemperature();

    public void showRegion() {
        System.out.println("DEFAULT");
    }
}
