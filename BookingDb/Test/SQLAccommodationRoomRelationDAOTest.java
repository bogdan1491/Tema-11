import org.junit.*;
import ro.homework.bookingdb.dao.sql.SQLAFRelationDAO;
import ro.homework.bookingdb.dao.sql.SQLAccommodationDAO;
import ro.homework.bookingdb.dao.sql.SQLRoomFairDAO;
import ro.homework.bookingdb.db.BookingDbCreator;
import ro.homework.bookingdb.db.BookingDbException;
import ro.homework.bookingdb.model.AFRelation;
import ro.homework.bookingdb.model.Accommodation;
import ro.homework.bookingdb.model.RoomFair;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SQLAccommodationRoomRelationDAOTest {
    private BookingDbCreator db;
    private SQLRoomFairDAO roomFairDAO;
    private SQLAccommodationDAO accommodationDAO;
    private SQLAFRelationDAO afRelationDAO;

    @BeforeClass
    public static void initTests() throws BookingDbException, SQLException {
        BookingDbCreator.setUpTestDB();
    }

    @AfterClass
    public static void discardTests() throws BookingDbException, SQLException {
        BookingDbCreator.dropTestDB();
    }

    @Before
    public void setUp() {
        db = new BookingDbCreator();
        roomFairDAO = new SQLRoomFairDAO(db);
        accommodationDAO = new SQLAccommodationDAO(db);
        afRelationDAO = new SQLAFRelationDAO(db);
    }

    @After
    public void tearDown() throws BookingDbException, SQLException {
        db.dropDataFromTables();
    }


    @Test
    public void testWhenNewAccommodationsRoomsAndRelationsAreInsertedIntoDB() throws BookingDbException, SQLException {
        RoomFair roomFair1 = new RoomFair();
        RoomFair roomFair2 = new RoomFair();
        Accommodation accommodation1 = new Accommodation();
        Accommodation accommodation2 = new Accommodation();
        AFRelation afRelation1 = new AFRelation();
        AFRelation afRelation2 = new AFRelation();


        accommodation1.setType("Hotel");
        accommodation1.setBedType("Double");
        accommodation1.setMaxGuests(100);
        accommodation1.setDescription("The Hotel Hesperia is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. It is a small, comfortable hotel, situated on the Canale di Cannaregio. The Derai family and their staff offer an attentive, personalized service and are always available to offer any help to guests.");
        accommodationDAO.add(accommodation1);

        accommodation2.setType("Motel");
        accommodation2.setBedType("Single");
        accommodation2.setMaxGuests(50);
        accommodation2.setDescription("The Motel Vienna is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. It is a small, comfortable hotel, situated on the Canale di Cannaregio. The Derai family and their staff offer an attentive, personalized service and are always available to offer any help to guests.");
        accommodationDAO.add(accommodation2);

        roomFair1.setValue(50.25);
        roomFair1.setSeason("Winter");


        roomFair2.setValue(70.75);
        roomFair2.setSeason("Summer");

        roomFairDAO.add(roomFair1);
        roomFairDAO.add(roomFair2);


        afRelation1.setIdAccommodation(accommodation1.getId());
        afRelation2.setIdAccommodation(accommodation2.getId());

        afRelation1.setIdRoomFair(roomFair1.getId());
        afRelation2.setIdRoomFair(roomFair2.getId());
        afRelationDAO.add(afRelation1);
        afRelationDAO.add(afRelation2);

    }

    @Test
    public void testWhenNewAccommodationsRoomsAndRelationsInsertedIntoDBAndAreRoomsArePrinted() throws BookingDbException, SQLException {
        RoomFair roomFair1 = new RoomFair();
        RoomFair roomFair2 = new RoomFair();
        Accommodation accommodation1 = new Accommodation();
        Accommodation accommodation2 = new Accommodation();
        AFRelation afRelation1 = new AFRelation();
        AFRelation afRelation2 = new AFRelation();


        accommodation1.setType("Hotel");
        accommodation1.setBedType("Double");
        accommodation1.setMaxGuests(100);
        accommodation1.setDescription("The Hotel Hesperia is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. It is a small, comfortable hotel, situated on the Canale di Cannaregio. The Derai family and their staff offer an attentive, personalized service and are always available to offer any help to guests.");
        accommodationDAO.add(accommodation1);

        accommodation2.setType("Motel");
        accommodation2.setBedType("Single");
        accommodation2.setMaxGuests(50);
        accommodation2.setDescription("The Motel Vienna is the right choice for visitors who are searching for a combination of charm, peace and quiet, and a convenient position from which to explore Venice. It is a small, comfortable hotel, situated on the Canale di Cannaregio. The Derai family and their staff offer an attentive, personalized service and are always available to offer any help to guests.");
        accommodationDAO.add(accommodation2);

        roomFair1.setValue(50.25);
        roomFair1.setSeason("Winter");


        roomFair2.setValue(70.75);
        roomFair2.setSeason("Summer");

        roomFairDAO.add(roomFair1);
        roomFairDAO.add(roomFair2);


        afRelation1.setIdAccommodation(accommodation1.getId());
        afRelation2.setIdAccommodation(accommodation2.getId());

        afRelation1.setIdRoomFair(roomFair1.getId());
        afRelation2.setIdRoomFair(roomFair2.getId());
        afRelationDAO.add(afRelation1);
        afRelationDAO.add(afRelation2);
        List<RoomFair> allPrices = roomFairDAO.getAllPrices();

        System.out.println(allPrices);

    }
}
