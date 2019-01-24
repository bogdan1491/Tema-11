package ro.homework.bookingdb.dao.sql;

import ro.homework.bookingdb.model.AFRelation;
import ro.homework.bookingdb.db.BookingDb;
import ro.homework.bookingdb.db.BookingDbException;
import ro.homework.bookingdb.dao.AFRelationDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLAFRelationDAO implements AFRelationDAO {
    private BookingDb db;

    public SQLAFRelationDAO(BookingDb db) {

        this.db = db;
    }

    @Override
    public List<AFRelation> getAll() throws BookingDbException, SQLException {
        try (Connection conn = db.connect()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from accommodation_fair_relation;");
            ArrayList<AFRelation> afRelations = new ArrayList<>();
            while (resultSet.next()) {
                AFRelation afRelation = mapResultSetToAFRelation(resultSet);
                afRelations.add(afRelation);
            }

            return afRelations;
        }
    }


    private AFRelation mapResultSetToAFRelation(ResultSet resultSet) throws SQLException {
        AFRelation afRelation = new AFRelation();
        afRelation.setId(resultSet.getInt("id"));
        afRelation.setIdAccommodation(resultSet.getInt("accommodation_id"));
        afRelation.setIdRoomFair(resultSet.getInt("room_fair_id"));
        return afRelation;
    }

    @Override
    public void add(AFRelation afRelation) throws BookingDbException, SQLException {
        try (Connection connection = db.connect()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO accommodation_fair_relation(accommodation_id, room_fair_id) values('" + afRelation.getIdAccommodation() + "', '" + afRelation.getIdRoomFair() + "');");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT CURRVAL('id_accommodation_fair_relation')");
            resultSet.next();
            afRelation.setId(resultSet.getInt(1));
        }
    }
}
