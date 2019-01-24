package ro.homework.bookingdb.db;

import java.sql.*;

public class BookingDbCreator extends BookingDb {
    private Connection connectToPostgreSQL() throws SQLException, BookingDbException {
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
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingDbException("Could not load DB driver.", e);
        }
    }

    @Override
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
                    .append("bookings")
                    .append("?user=")
                    .append("postgres")
                    .append("&password=")
                    .append("postgres").toString();
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new BookingDbException("Could not load DB driver.", e);
        }
    }
    public static void setUpTestDB() throws BookingDbException, SQLException {
        BookingDbCreator tdb = new BookingDbCreator();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE DATABASE bookings;");
        }
        try(Connection connection = tdb.connect()) {
            StringBuilder builder = new StringBuilder();
            builder.append("CREATE SEQUENCE id_accommodation;");
            builder.append("CREATE TABLE accommodation(id INT PRIMARY KEY DEFAULT NEXTVAL('id_accommodation'), type VARCHAR(32), bed_type VARCHAR(32), max_guests INT, description VARCHAR(512));");
            builder.append("CREATE SEQUENCE id_room_fair;");
            builder.append("CREATE TABLE room_fair(id INT PRIMARY KEY DEFAULT NEXTVAL('id_room_fair'), value DOUBLE PRECISION, season VARCHAR(32));");
            builder.append("CREATE SEQUENCE id_accommodation_fair_relation;");
            builder.append("CREATE TABLE accommodation_fair_relation(id INT PRIMARY KEY DEFAULT NEXTVAL('id_accommodation_fair_relation'), accommodation_id INT REFERENCES accommodation(id), room_fair_id INT REFERENCES room_fair(id));");

            Statement statement;
            statement = connection.createStatement();
            statement.execute(builder.toString());
        }
    }

    public static void dropTestDB() throws BookingDbException, SQLException {
        BookingDbCreator tdb = new BookingDbCreator();
        try(Connection connection = tdb.connectToPostgreSQL()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP DATABASE bookings;");
        }
    }

    public void dropDataFromTables() throws BookingDbException, SQLException {
        try (Connection connection = connect()) {
            PreparedStatement deletionPs = null;

            try {
                deletionPs = connection.prepareStatement("DELETE FROM accommodation_fair_relation");
                deletionPs.executeUpdate();
                deletionPs = connection.prepareStatement("DELETE FROM accommodation");
                deletionPs.executeUpdate();
                deletionPs = connection.prepareStatement("DELETE FROM room_fair");
                deletionPs.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Cannot delete tables: " + e.getMessage());

            } finally {
                if (deletionPs != null) {
                    try {
                        deletionPs.close();

                    } catch (SQLException e) {
                        System.out.println("Prepared Statement could not be closed: " + e.getMessage());
                    }
                }
            }
        }
    }

}
