package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CellItem implements Parcelable {
    /*
        Domain model that will be used by RetroFit2 in order to get the JSON via rest and generate
        a property model from it.
        Implementing it as Parcelable, so that we can save this in onSaveInstance bundle.
    */

    @SerializedName("id")
    String id;
    @SerializedName("type")
    String type;
    @SerializedName("message")
    String message;
    @SerializedName("typefield")
    String typefield;
    @SerializedName("hidden")
    String hidden;
    @SerializedName("topSpacing")
    String topSpacing;
    @SerializedName("show")
    String show;
    @SerializedName("required")
    String required;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return
                "Id: " + id +
                        "\ntype: " + type +
                        "\nmessage: " + message +
                        "\ntypefield: " + typefield +
                        "\nhidden: " + hidden +
                        "\ntopSpacing: " + topSpacing +
                        "\nshow: " + show +
                        "\nrequired: " + required;

    }

    protected CellItem(Parcel in) {
        id = in.readString();
        type = in.readString();
        message = in.readString();
        typefield = in.readString();
        hidden = in.readString();
        topSpacing = in.readString();
        show = in.readString();
        required = in.readString();
    }

    public static final Creator<CellItem> CREATOR = new Creator<CellItem>() {
        @Override
        public CellItem createFromParcel(Parcel in) {
            return new CellItem(in);
        }

        @Override
        public CellItem[] newArray(int size) {
            return new CellItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(type);
        parcel.writeString(message);
        parcel.writeString(typefield);
        parcel.writeString(hidden);
        parcel.writeString(topSpacing);
        parcel.writeString(show);
        parcel.writeString(required);
    }
}
