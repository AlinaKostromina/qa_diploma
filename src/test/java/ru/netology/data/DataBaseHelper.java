package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DataBaseHelper {

    private static final QueryRunner runner = new QueryRunner();

    private DataBaseHelper() {
    }

    public static Connection getConn() throws SQLException {
        return getConnection(System.getProperty("db.url"), "app", "pass");
    }

    @SneakyThrows
    public static String getStatusCreditRequest() {
        var codesql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        var conn = getConn();
        var countStatement = conn.createStatement();
        var resultSet = countStatement.executeQuery(codesql);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static String getStatusPaymentRequest() {
        var codesql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        var conn = getConn();
        var countStatement = conn.createStatement();
        var resultSet = countStatement.executeQuery(codesql);
        if (resultSet.next()) {
            return resultSet.getString("status");
        }
        return null;
    }

    @SneakyThrows
    public static int getCountOrderEntity() {
        var codesql = "SELECT COUNT(*) AS total FROM order_entity;";
        var conn = getConn();
        var countStatement = conn.createStatement();
        var resultSet = countStatement.executeQuery(codesql);
        if (resultSet.next()) {
            return resultSet.getInt("total");
        }
        return 0;
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}