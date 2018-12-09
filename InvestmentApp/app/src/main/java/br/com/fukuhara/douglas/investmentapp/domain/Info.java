package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Info implements Parcelable {

    /*
        Domain model that will be used by RetroFit2 in order to get the JSON via rest and generate
        a property model from it.
        Implementing it as Parcelable, so that we can save this in onSaveInstance bundle.
    */

    @SerializedName("name")
    private String name;
    @SerializedName("data")
    private String data;

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "{ name: " + this.name + ", data: " + this.data + " }";
    }

    protected Info(Parcel in) {
        name = in.readString();
        data = in.readString();
    }

    public static final Creator<Info> CREATOR = new Creator<Info>() {
        @Override
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        @Override
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(data);
    }
}
