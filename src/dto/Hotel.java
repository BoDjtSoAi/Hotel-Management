package dto;

import java.io.Serializable;

public class Hotel implements Serializable {
    private String id;
    private String name;
    private int roomAvailable;
    private String address;
    private String phone;
    private float rating;
    public Hotel() {
    }

    public Hotel(String id, String name, int roomAvailable, String address, String phone, float rating) {
        this.id = id;
        this.name = name;
        this.roomAvailable = roomAvailable;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public float getRating() {
        return rating;
    }
}
