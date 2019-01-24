package ro.homework.bookingdb.dao.sql;

import ro.homework.bookingdb.db.BookingDb;
import ro.homework.bookingdb.db.BookingDbException;
import ro.homework.bookingdb.model.RoomFair;
import ro.homework.bookingdb.dao.RoomFairDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomFairDAO implements RoomFairDAO {
    private BookingDb db;

    public SQLRoomFairDAO(BookingDb db) {

        this.db = db;
    }

    @Override
    public List<RoomFair> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from room_fair;");
            ArrayList<RoomFair> roomFairs = new ArrayList<>();
            while (resultSet.next()) {
                RoomFair roomFair = mapResultSetToRoomFair(resultSet);
                roomFairs.add(roomFair);
            }
            return roomFairs;
        }
    }

    public List<RoomFair> getAllPrices() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from room_fair;");
            ArrayList<RoomFair> roomFairs = new ArrayList<>();
            while (resultSet.next()) {
                RoomFair roomFair = mapResultSetToRoomFair(resultSet);
                roomFairs.add(roomFair);
            }
            System.out.println("The available rooms from the database are: ");
            return roomFairs;
        }
    }

    private RoomFair mapResultSetToRoomFair(ResultSet resultSet) throws SQLException {
        RoomFair roomFair = new RoomFair();
        roomFair.setId(resultSet.getInt("id"));
        roomFair.setValue(resultSet.getDouble("value"));
        roomFair.setSeason(resultSet.getString("season"));
        return roomFair;
    }

    @Override
    public void delete(RoomFair roomFair) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM room_fair WHERE id=" + roomFair.getId() + ";");
        }
    }

    @Override
    public void add(RoomFair roomFair) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO room_fair(value, season) values('" + roomFair.getValue() + "', '" + roomFair.getSeason() + "');");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('id_room_fair')");
            resultSet.next();
            roomFair.setId(resultSet.getInt(1));
        }
    }

    @Override
    public void update(RoomFair roomFair) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE room_fair SET value = '" + roomFair.getValue() + "', season = '" + roomFair.getSeason() + "' WHERE id = " + roomFair.getId() + ";");
        }
    }
}
