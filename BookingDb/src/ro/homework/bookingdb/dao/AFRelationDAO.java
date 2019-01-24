package ro.homework.bookingdb.dao;

import ro.homework.bookingdb.model.AFRelation;
import ro.homework.bookingdb.db.BookingDbException;

import java.sql.SQLException;
import java.util.List;

public interface AFRelationDAO {
    List<AFRelation> getAll() throws Exception, BookingDbException;


    void add(AFRelation afRelation) throws BookingDbException, SQLException;

}
