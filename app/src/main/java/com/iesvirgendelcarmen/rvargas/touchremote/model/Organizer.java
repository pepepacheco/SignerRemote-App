package com.iesvirgendelcarmen.rvargas.touchremote.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Organizer implements Parcelable {

    @SerializedName("id")
    private String id;

    public Organizer(String id) {
        this.id = id;
    }

    protected Organizer(Parcel in) {
        id = in.readString();
    }

    public static final Creator<Organizer> CREATOR = new Creator<Organizer>() {
        @Override
        public Organizer createFromParcel(Parcel in) {
            return new Organizer(in);
        }

        @Override
        public Organizer[] newArray(int size) {
            return new Organizer[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Organizer{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
    }
}
