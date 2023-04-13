package testNG;

import db.DBConnection;

import java.sql.Connection;

public class TestConnectDB {
    public static void main(String[] args) {
        //Kết nối với DB
        Connection connection = DBConnection.getConnectionDB();
        System.out.println(connection);

        DBConnection.printInfo(connection);
        //Khi ko tìm thấy connection => close
        DBConnection.closeConnection(connection);
        System.out.println(connection);

    }
}
