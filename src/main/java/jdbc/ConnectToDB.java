package jdbc;

import entitys.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectToDB implements AutoCloseable {
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/db_test";
    private static Connection conn;
    /**
     * Creates new instance
     * @throws SQLException in case of connection issue
     */
    public ConnectToDB() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
    }
    /**
     * Queries db to get most popular flight route for ths given year
     * @throws SQLException in case of query issue
     */
    public static List<Shop> getShops() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select * from db_test.shop";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        List <Shop> listShops =  new ArrayList<>();
        while (rs.next()) {
                listShops.add(new Shop(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
        }
        return listShops;
    }

    public static List<String> getSt() {
        List <String> l =  new ArrayList<>();
        return l;
    }


    public static void main() throws Exception {
        try (ConnectToDB demo = new ConnectToDB()) {
            demo.getShops();
        }
    }

    @Override
    public void close() throws Exception {

    }
}

