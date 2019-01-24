package ro.homework.bookingdb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BookingDb {
    public Connection connect() throws BookingDbException, SQLException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            Connection connection = null;
            DriverManager.setLoginTimeout(60);
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append("postgresql")       // “mysql” / “db2” / “mssql” / “oracle” / ...
                    .append("://")
                    .append("localhost")
                    .append(":")
                    .append(5432)
                    .append("/")
                    .append("orders")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingDbException("Could not load DB driver.", e);
        }
    }
}
