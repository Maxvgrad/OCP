package org.oca.interfaces;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterfaceTest {


    @Test
    void staticMethod() {
        Office off = new HomeOffice();
        //System.out.println(off.getAddress()); //static method
        assertEquals("101 Smart Str", Office.getAddress());
        assertEquals("R No 1, Home", ((HomeOffice) off).getAddress());
    }

    @Test
    void staticAndDefaultMethods() {
        House house = new OpenOffice();
        assertEquals("101 Main Str", house.getAddress());
    }

    interface House {
        default String getAddress() {
            return "101 Main Str";
        }
    }

    interface Office {
        static String getAddress() {
            return "101 Smart Str";
        }
    }

    interface Spot {
        String getAddress();
    }

    interface Area {
        default String getAddress() {
            return "15 Saint P";
        }
    }

    interface Island {
        default void getAddress() {
            return;
        }
    }

    interface WFH extends House, Office {}

    class HomeOffice implements House, Office {
        public String getAddress() {
            return "R No 1, Home";
        }
    }

    class OpenOffice implements WFH {
    }

    class WorkArea implements Spot, House {
        @Override
        public String getAddress() {
            return "Have to implement abstract method from Spot interface!";
        }
    }

    class ClassWithTwoDefaultMethods implements Area, House {
        @Override
        public String getAddress() {
            return "Have to override because of two default methods.";
        }
    }

    //class ClassWithTwoDefaultMethodsWithoutOverriding implements Area, House {

    //}

    //class WithTwoSameMethodsButWithDiffReturnType implements House, Island { //incompatible return type

    //}


    interface Container {
        int VOLUME = 4;
    }

    interface Box {
        int VOLUME = 2;
    }

    class Volumable implements Container, Box {
        //just for test (:
        private int getVolumeWithoutCust() {
            int v = Container.VOLUME;
            v = Box.VOLUME;
            Volumable volumable = new Volumable();
            return ((Box)volumable).VOLUME;
        }
    }

    interface Account {
        default String getId() {
            return "00";
        }
    }

    interface PremiumAccount extends Account {
        String getId();
    }


    class AccountImpl implements PremiumAccount {

        @Override
        public String getId() {
            return null;
        }
    }

    interface Db {
        void create() throws SQLException;
    }

    interface FileSystem {
        void create() throws IOException;
    }

    class FileSystemDb implements Db, FileSystem {

        @Override
        public void create() { // without throws

        }
    }

}
