package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DownInfo implements Parcelable {

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
        return "{ name: '" + this.name + "', data: '" + this.data + "' }";
    }

    public DownInfo(Parcel in) {
        name = in.readString();
        data = in.readString();
    }

    public static final Creator<DownInfo> CREATOR = new Creator<DownInfo>() {
        @Override
        public DownInfo createFromParcel(Parcel in) {
            return new DownInfo(in);
        }

        @Override
        public DownInfo[] newArray(int size) {
            return new DownInfo[size];
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
