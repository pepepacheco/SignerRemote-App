package com.iesvirgendelcarmen.rvargas.touchremote.model;

import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("userId")
    private String idUser;

    @SerializedName("employeeId")
    private String idEmployee;

    @SerializedName("notificationId")
    private String idNotification;

    @SerializedName("longitude")
    private double longiude;

    @SerializedName("latitude")
    private double latitude;

    public Registration(String idUser, String idEmployee, String idNotification, double longiude, double latitude) {
        this.idUser = idUser;
        this.idEmployee = idEmployee;
        this.idNotification = idNotification;
        this.longiude = longiude;
        this.latitude = latitude;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(String idNotification) {
        this.idNotification = idNotification;
    }

    public double getLongiude() {
        return longiude;
    }

    public void setLongiude(double longiude) {
        this.longiude = longiude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "idUser='" + idUser + '\'' +
                ", idEmployee='" + idEmployee + '\'' +
                ", idNotification='" + idNotification + '\'' +
                ", longiude=" + longiude +
                ", latitude=" + latitude +
                '}';
    }
}
