package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Fund implements Parcelable {

    /*
        Domain model that will be used by RetroFit2 in order to get the JSON via rest and generate
        a property model from it.
        Implementing it as Parcelable, so that we can save this in onSaveInstance bundle.
    */

    @SerializedName("screen")
    private Screen screen;

    public Screen getScreen() {
        return this.screen;
    }

    protected Fund(Parcel in) {
        screen = in.readParcelable(Screen.class.getClassLoader());
    }

    public static final Creator<Fund> CREATOR = new Creator<Fund>() {
        @Override
        public Fund createFromParcel(Parcel in) {
            return new Fund(in);
        }

        @Override
        public Fund[] newArray(int size) {
            return new Fund[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(screen, i);
    }
}
