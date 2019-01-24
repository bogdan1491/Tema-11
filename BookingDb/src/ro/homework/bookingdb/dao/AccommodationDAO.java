package ro.homework.bookingdb.dao;

import ro.homework.bookingdb.model.Accommodation;
import ro.homework.bookingdb.db.BookingDbException;

import java.sql.SQLException;
import java.util.List;

public interface AccommodationDAO {
    List<Accommodation> getAll() throws Exception, BookingDbException;

    void delete(Accommodation accommodation) throws BookingDbException, SQLException;

    void add(Accommodation accommodation) throws BookingDbException, SQLException;

    void update(Accommodation accommodation) throws BookingDbException, SQLException;
}
