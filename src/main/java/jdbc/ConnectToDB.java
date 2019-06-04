package jdbc;

import entitys.Shop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void saveCheck(String shop_id, Double sum) throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "insert into db_test.shop (id_shop, id , date_time, sum ) select " + shop_id + ", generateUUIDv4(), now()-30000, " + sum;
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

    public static Shop getShop(String shop_id) throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select * from db_test.shop where id='" + shop_id+ "'";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        Shop shop = new Shop("","","");
        while (rs.next()) {
            shop = new Shop(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3));
        }
        return shop;
    }



    @Override
    public void close() throws Exception {

    }
}

