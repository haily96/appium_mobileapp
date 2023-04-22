package db;
import java.sql.*;
public class DBConnection {
    public static Connection getConnectionDB() {
        Connection connection = null;
        try {
            DriverManager.registerDriver( new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=kltn_appium", "sa2", "sa2");
            System.out.println("Connect DB: ok");
        } catch (SQLException e){
            System.err.println("Lỗi kết nối DB");
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection (Connection connection){
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e){
            System.out.println("close connection");
        }
    }
    public static void printInfo(Connection connection){
        try {
            if (connection != null) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();
                System.out.println("Tool: " + databaseMetaData.getDatabaseProductName());
            }
        } catch (SQLException e){
            System.out.println("close connection");
            e.printStackTrace();
        }
    }
}

