package jdbc;

import entitys.Shop;
import entitys.infoDay;

import java.awt.*;
import java.sql.*;
import java.util.*;
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

    public static void saveCheck(String shop_id, Double sum) throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        Random rnd = new Random();
        String query = "insert into db_test.check (id_shop, id , date_time, sum ) select '" + shop_id + "', generateUUIDv4(), now()-" + rnd.nextInt(1000000) + ", " + sum;
        Statement statement = conn.createStatement();
        statement.executeQuery(query);
    }

    public static List<Shop> getShops() throws SQLException {
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select * from db_test.shop";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        List <Shop> listShops = new ArrayList<>();
        while (rs.next()) {
                listShops.add(new Shop(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3)));
        }
        return listShops;
    }

    public static List<Shop> getShops(String shops) throws SQLException {
        String[] shop = shops.split(",");
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select * from db_test.shop where id ='";
        int i = 0;
        for (String s: shop) {
            if (i == 0) {
                query += s + "'";
            }
            else {
                query += " or id='" + s + "'";
            }
            i++;
        }
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        List <Shop> listShops = new ArrayList<>();
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

    public static void generateCheck(String shop_id) throws SQLException {
            saveCheck(shop_id,1.1);

    }

    public static List<Double> getStatistic(String shop_id, String date) throws SQLException {
        String[] d = date.split(",");
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select sum(sum), avg(sum), count(sum) from db_test.summing_check where id_shop = '" + shop_id + "'\n" +
                "                                                and date_time >= toDate('" + d[0] + "')\n" +
                "                                                and date_time <= toDate('" + d[1] + "')\n" +
                "                                                group by id_shop";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        List<Double> doubleList = new ArrayList<>();
        while (rs.next()) {
            doubleList.add(Double.parseDouble(rs.getString(1)));
            doubleList.add(Double.parseDouble(rs.getString(2)));
            doubleList.add(Double.parseDouble(rs.getString(3)));
        }
        return doubleList;
    }

    public static List<infoDay> getStatisticEachDay(String shop_id, String date) throws SQLException {
        String[] d = date.split(",");
        conn = DriverManager.getConnection(DB_URL,"","1");
        String query = "select sum(sum), count(sum), toDate(date_time) from db_test.summing_check where id_shop = '" + shop_id + "'\n" +
                "                                                and date_time >= toDate('" + d[0] + "')\n" +
                "                                                and date_time <= toDate('" + d[1] + "')\n" +
                "                                                group by id_shop, toDate(date_time)\n" +
                "                                                order by toDate(date_time)";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        List <infoDay> infoDayList = new ArrayList<>();
        while (rs.next()) {
            infoDayList.add(new infoDay( Integer.valueOf(rs.getString(2)),Double.valueOf(rs.getString(1)),rs.getString(3)));
        }
        return infoDayList;
    }

    @Override
    public void close() throws Exception {

    }
}

