package com.example.cocinamama.usecases.homeFragments;

import android.os.Parcel;
import android.os.Parcelable;

public class dishModel implements Parcelable {
    private String title;
    private String description;
    private String photo;

    public dishModel(String title, String description, String photo) {
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    protected dishModel(Parcel parcel) {
        title = parcel.readString();
        description = parcel.readString();
        photo = parcel.readString();
    }

    public static final Creator<dishModel> CREATOR = new Creator<dishModel>() {
        @Override
        public dishModel createFromParcel(Parcel in) {
            return new dishModel(in);
        }

        @Override
        public dishModel[] newArray(int size) {
            return new dishModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.photo);
    }
}
