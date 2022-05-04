package com.example.cocinamama.usecases.homeFragments;

import android.os.Parcel;
import android.os.Parcelable;

public class dishModel implements Parcelable {
    private Integer publicationdetail_id;
    private String title;
    private String description;
    private Number price;
    private String photo;

    public dishModel(Integer publicationdetail_id, String title, String description, Number price,String photo) {
        this.publicationdetail_id = publicationdetail_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    protected dishModel(Parcel parcel) {
        publicationdetail_id = parcel.readInt();
        title = parcel.readString();
        description = parcel.readString();
        price = parcel.readDouble();
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

    public Integer getId() {return  publicationdetail_id;}

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public  Number getPrice(){return  price;}

    public String getPhoto() {
        return photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.publicationdetail_id);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeDouble((Double) this.price);
        parcel.writeString(this.photo);
    }
}
