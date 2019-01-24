package ro.homework.bookingdb.dao;

import ro.homework.bookingdb.db.BookingDbException;
import ro.homework.bookingdb.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

public interface RoomFairDAO {
    List<RoomFair> getAll() throws Exception, BookingDbException;

    void delete(RoomFair roomFair) throws BookingDbException, SQLException;

    void add(RoomFair roomFair) throws BookingDbException, SQLException;

    void update(RoomFair roomFair) throws BookingDbException, SQLException;
}
