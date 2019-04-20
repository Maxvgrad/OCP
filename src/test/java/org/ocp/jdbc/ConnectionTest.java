package org.ocp.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

class ConnectionTest {

    private Connection connection;


    @Test
    void classInterface() throws Exception {
        connection.createStatement();
        connection.prepareStatement("sql");
        connection.createStatement();
        connection.rollback();

    }
}
