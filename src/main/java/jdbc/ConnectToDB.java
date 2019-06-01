package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectToDB implements AutoCloseable {
    private static final String DB_URL = "jdbc:clickhouse://localhost:8123/db_test";
    private final Connection conn;

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
    public void getShops() throws SQLException {
        String query = "select * from db_test.events where site_id = 1";
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(query);
        ResultSetMetaData rsMetadata = rs.getMetaData();
        while (rs.next()) {
            for (int i = 1; i <= rsMetadata.getColumnCount(); i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public static List<String> getSt() {
        List <String> l =  new ArrayList<>();
        l.add("dd1");
        l.add("dd2");
        l.add("dd3");
        l.add("dd4");
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

