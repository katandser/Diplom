package jdbc;

import entitys.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectToDB implements AutoCloseable {
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/db_test";
    private static Connection conn;

    public ConnectToDB() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
    }

    public static void saveShop(String adress, String name ) throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "insert into db_test.shop (id, name, adress) select generateUUIDv4(), '" + name + "', '" + adress + "'";
        Statement statement = conn.createStatement();
        statement.executeQuery(query);
    }

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


    @Override
    public void close() throws Exception {

    }
}

