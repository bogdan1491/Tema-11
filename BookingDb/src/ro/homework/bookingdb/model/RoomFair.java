package ro.homework.bookingdb.model;

import java.util.Objects;

public class RoomFair {
    private int id;
    private double value;
    private String season;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
    @Override
    public String toString() {
        return "Room" + " " + id + ":" +
                " value =" + " " + value + " Euro" + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomFair roomFair = (RoomFair) o;
        return id == roomFair.id &&
                Double.compare(roomFair.value, value) == 0 &&
                Objects.equals(season, roomFair.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, season);
    }
}
