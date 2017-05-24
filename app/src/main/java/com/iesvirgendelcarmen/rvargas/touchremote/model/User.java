package com.iesvirgendelcarmen.rvargas.touchremote.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("id")
    private String id;

    @SerializedName("user")
    private Organizer organizer;

    @SerializedName("name")
    private String name;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("workstation")
    private String workstation;

    @SerializedName("change_password")
    private boolean changePassword;

    @SerializedName("woman")
    private Boolean woman;

    public User(String id, Organizer organizer, String name, String lastName, String email, String workstation, Boolean changePassword, Boolean woman) {
        this.id = id;
        this.organizer = organizer;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.changePassword = changePassword;
        this.workstation = workstation;
        this.woman = woman;
    }

    protected User(Parcel in) {
        id = in.readString();
        organizer = in.readParcelable(Organizer.class.getClassLoader());
        name = in.readString();
        lastName = in.readString();
        email = in.readString();
        workstation = in.readString();
        changePassword = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getWorkstation() {
        return workstation;
    }

    public void setWorkstation(String workstation) {
        this.workstation = workstation;
    }

    public boolean isWoman() {
        return this.woman;
    }

    public void setWoman(Boolean woman) {
        this.woman = woman;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", organizer=" + organizer +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", workstation='" + workstation + '\'' +
                ", changePassword='" + changePassword + '\'' +
                ", woman=" + woman +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(organizer, flags);
        dest.writeString(name);
        dest.writeString(lastName);
        dest.writeString(email);
        dest.writeString(workstation);
        dest.writeByte((byte) (changePassword ? 1 : 0));
    }
}