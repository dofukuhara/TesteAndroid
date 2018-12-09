package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Screen implements Parcelable {

    /*
        Domain model that will be used by RetroFit2 in order to get the JSON via rest and generate
        a property model from it.
        Implementing it as Parcelable, so that we can save this in onSaveInstance bundle.
    */

    @SerializedName("title")
    private String title;

    @SerializedName("fundName")
    private String fundName;

    @SerializedName("whatIs")
    private String whatIs;

    @SerializedName("definition")
    private String definition;

    @SerializedName("riskTitle")
    private String riskTitle;

    @SerializedName("risk")
    private String risk;

    @SerializedName("infoTitle")
    private String infoTitle;

    @SerializedName("moreInfo")
    private MoreInfo moreInfo;

    @SerializedName("info")
    private ArrayList<Info> info;

    @SerializedName("downInfo")
    private ArrayList<DownInfo> downInfo;

    protected Screen(Parcel in) {
        title = in.readString();
        fundName = in.readString();
        whatIs = in.readString();
        definition = in.readString();
        riskTitle = in.readString();
        risk = in.readString();
        infoTitle = in.readString();
        moreInfo = in.readParcelable(MoreInfo.class.getClassLoader());
        info = in.createTypedArrayList(Info.CREATOR);
        downInfo = in.createTypedArrayList(DownInfo.CREATOR);
    }

    public static final Creator<Screen> CREATOR = new Creator<Screen>() {
        @Override
        public Screen createFromParcel(Parcel in) {
            return new Screen(in);
        }

        @Override
        public Screen[] newArray(int size) {
            return new Screen[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getFundName() {
        return fundName;
    }

    public String getWhatIs() {
        return whatIs;
    }

    public String getDefinition() {
        return definition;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public String getRisk() {
        return risk;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public MoreInfo getMoreInfo() {
        return moreInfo;
    }

    public ArrayList<Info> getInfo() {
        return info;
    }

    public ArrayList<DownInfo> getDownInfo() {
        return downInfo;
    }

    @Override
    public String toString() {
        return "screen {" +
                "title='" + title + '\'' +
                ", fundName='" + fundName + '\'' +
                ", whatIs='" + whatIs + '\'' +
                ", definition='" + definition + '\'' +
                ", riskTitle='" + riskTitle + '\'' +
                ", risk='" + risk + '\'' +
                ", infoTitle='" + infoTitle + '\'' +
                ", moreInfo=" + moreInfo +
                ", info=" + info +
                ", downInfo=" + downInfo +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(fundName);
        parcel.writeString(whatIs);
        parcel.writeString(definition);
        parcel.writeString(riskTitle);
        parcel.writeString(risk);
        parcel.writeString(infoTitle);
        parcel.writeParcelable(moreInfo, i);
        parcel.writeTypedList(info);
        parcel.writeTypedList(downInfo);
    }

}