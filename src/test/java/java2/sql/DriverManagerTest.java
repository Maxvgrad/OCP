package java2.sql;

import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

class DriverManagerTest {


    @Test
    void getLoginTimeout() {
        System.out.println(DriverManager.getLoginTimeout());
    }

    @Test
    void getDrivers() {
        for (Enumeration<Driver> drivers = DriverManager.getDrivers(); drivers.hasMoreElements();) {
            System.out.println(drivers.nextElement());
        }
    }
}
