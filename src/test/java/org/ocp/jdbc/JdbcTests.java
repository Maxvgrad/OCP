package org.ocp.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JdbcTests extends BaseTest {

    @Test
    void insert() throws Exception {
        String sql = "insert into student (name) values ('Max')";

        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            int numOfRecords = statement.executeUpdate(sql);
            assertEquals(1, numOfRecords);
        }
    }

    @Test
    void selectCount() throws Exception {
        String sql = "select count(1) from student";

        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sql);

            ResultSet resultSet = statement.getResultSet();
            int numOfRows = -1;
            if (resultSet.next()) {
                numOfRows = resultSet.getInt(1);
            }

            assertEquals(0, numOfRows);
        }
    }


    @Test
    void noCommitOnClose() throws Exception {
        String sqlInsert = "insert into student (name) values (?)";
        String sqlCount = "select count(1) from student";

        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
        ) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, "Max");
            int numOfInsertedRows = preparedStatement.executeUpdate();
            assertEquals(1, numOfInsertedRows);
        }

        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sqlCount);
            int numOfRows = extractInt(statement.getResultSet());

            assertEquals(0, numOfRows);
        }
    }

    @Test
    void commitBeforeClose() throws Exception {
        String sqlInsert = "insert into student (name) values (?)";
        String sqlCount = "select count(1) from student";

        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
             Statement statement = connection.createStatement()
        ) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, "Max");
            int numOfInsertedRows = preparedStatement.executeUpdate();
            assertEquals(1, numOfInsertedRows);

            statement.execute(sqlCount);
            int numOfRows = extractInt(statement.getResultSet());

            assertEquals(1, numOfRows);

            connection.setAutoCommit(true);
        }

        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute(sqlCount);
            int numOfRows = extractInt(statement.getResultSet());

            assertEquals(1, numOfRows);
        }
    }

    @Test
    void selectFromStudentAndParseRs() throws Exception {

        try (Connection connection = getDataSource().getConnection();
             Statement statement = connection.createStatement();) {

            String sql = "select ID, NAME, GPA from student where 1=1";
            statement.execute(sql);

            ResultSet rs = statement.getResultSet();

            while (rs.next()) {

                System.out.print("Student(id: " + rs.getInt("id"));
                System.out.print(", name: " + rs.getObject("name"));
                System.out.println(", gpa: " + rs.getString("GPA") + ")");

            }

        }
    }

    @Test
    void rollbackToSavePoint() throws Exception {
        //given
        String sqlInsert = "insert into student (name) values (?)";
        String sqlCount = "select count(1) from student";
        try (Connection connection = getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
             Statement statement = connection.createStatement()) {

            //and given
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "Max");
            preparedStatement.executeUpdate();

            //when
            Savepoint savepoint = connection.setSavepoint("SavePoint-1");

            preparedStatement.setString(1, "Jon");
            preparedStatement.executeUpdate();

            //and when rollback to SavePoint-1
            connection.rollback(savepoint);

            connection.commit();

            //then expect only one row in db
            statement.execute(sqlCount);
            int numOfRows = extractInt(statement.getResultSet());
            assertEquals(1, numOfRows);
        }
    }

    private int extractInt(ResultSet rs) throws Exception {
        int numOfRows = -1;
        if (rs.next()) {
            numOfRows = rs.getInt(1);
        }
        return numOfRows;
    }
}
