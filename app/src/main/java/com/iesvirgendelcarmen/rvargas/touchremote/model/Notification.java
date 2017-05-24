package com.iesvirgendelcarmen.rvargas.touchremote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Notification implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("raidus")
    private int radius;

    @SerializedName("address")
    private String address;

    @SerializedName("send_date")
    private Date sendDate;

    @SerializedName("day_repeat")
    private List<DayRepeat> daysRepeat;

    public Notification(String id, double longitude, double latitude, int radius, String address, Date sendDate, List<DayRepeat> daysRepeat) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.radius = radius;
        this.address = address;
        this.sendDate = sendDate;
        this.daysRepeat = daysRepeat;
    }

    protected Notification(Parcel in) {
        id = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
        radius = in.readInt();
        address = in.readString();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getSendDateFormat() {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyy");
        String dateFormat = formatter.format(this.sendDate);

        return dateFormat;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public List<DayRepeat> getDaysRepeat() {
        return daysRepeat;
    }

    public void setDaysRepeat(List<DayRepeat> daysRepeat) {
        this.daysRepeat = daysRepeat;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", radius=" + radius +
                ", address='" + address + '\'' +
                ", sendDate=" + sendDate +
                ", daysRepeat=" + daysRepeat +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeInt(radius);
        dest.writeString(address);
    }
}