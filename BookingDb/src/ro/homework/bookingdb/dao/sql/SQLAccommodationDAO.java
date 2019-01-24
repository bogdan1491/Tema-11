package ro.homework.bookingdb.dao.sql;

import ro.homework.bookingdb.model.Accommodation;
import ro.homework.bookingdb.db.BookingDb;
import ro.homework.bookingdb.db.BookingDbException;
import ro.homework.bookingdb.dao.AccommodationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAccommodationDAO implements AccommodationDAO {
    private BookingDb db;

    public SQLAccommodationDAO(BookingDb db) {

        this.db = db;
    }

    @Override
    public List<Accommodation> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation;");
            ArrayList<Accommodation> accommodations = new ArrayList<>();
            while (resultSet.next()) {
                Accommodation accommodation = mapResultSetToAccommodation(resultSet);
                accommodations.add(accommodation);
            }
            return accommodations;
        }
    }
//builder.append("CREATE SEQUENCE id_accommodation;");
//builder.append("CREATE TABLE accommodation(id INT PRIMARY KEY DEFAULT NEXTVAL('id_accommodation'), type VARCHAR(32) NOT NULL, bed_type VARCHAR(32) NOT NULL, max_guests INT NOT NULL);");

    private Accommodation mapResultSetToAccommodation(ResultSet resultSet) throws SQLException {
        Accommodation accommodation = new Accommodation();
        accommodation.setId(resultSet.getInt("id"));
        accommodation.setType(resultSet.getString("type"));
        accommodation.setBedType(resultSet.getString("bed_type"));
        accommodation.setMaxGuests(resultSet.getInt("max_guests"));
        accommodation.setDescription(resultSet.getString("description"));

        return accommodation;
    }

    @Override
    public void delete(Accommodation accommodation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM accommodation WHERE id=" + accommodation.getId() + ";");
        }
    }

    @Override
    public void add(Accommodation accommodation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO accommodation(type, bed_type, max_guests, description) values('" + accommodation.getType() + "', '" + accommodation.getBedType() + "', '" + accommodation.getMaxGuests() + "', '" + accommodation.getDescription() + "');");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('id_accommodation')");
            resultSet.next();
            accommodation.setId(resultSet.getInt(1));
        }
    }

    @Override
    public void update(Accommodation accommodation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE accommodation SET type = '" + accommodation.getType() + "', bed_type = '" + accommodation.getBedType() + "', max_guests = '" + accommodation.getMaxGuests() + "' WHERE id = " + accommodation.getId() + ";");
        }
    }

}
