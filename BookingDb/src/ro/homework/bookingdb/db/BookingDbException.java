package ro.homework.bookingdb.db;

public class BookingDbException extends Throwable {
    public BookingDbException(String s, Exception e) {
        super(s, e);
    }
}
