package br.com.fukuhara.douglas.investmentapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MoreInfo implements Parcelable {

    /*
        Domain model that will be used by RetroFit2 in order to get the JSON via rest and generate
        a property model from it.
        Implementing it as Parcelable, so that we can save this in onSaveInstance bundle.
    */

    @SerializedName("month")
    private InfoData month;
    @SerializedName("year")
    private InfoData year;
    @SerializedName("12months")
    private InfoData twelvemonths;

    public InfoData getMonth() {
        return month;
    }

    public InfoData getYear() {
        return year;
    }

    public InfoData getTwelvemonths() {
        return twelvemonths;
    }

    @Override
    public String toString() {
        return "{ month: " + this.month + ", year: " + this.year + ", 12months: " + this.twelvemonths + " }";
    }

    protected MoreInfo(Parcel in) {
        month = in.readParcelable(InfoData.class.getClassLoader());
        year = in.readParcelable(InfoData.class.getClassLoader());
        twelvemonths = in.readParcelable(InfoData.class.getClassLoader());
    }

    public final Creator<MoreInfo> CREATOR = new Creator<MoreInfo>() {
        @Override
        public MoreInfo createFromParcel(Parcel in) {
            return new MoreInfo(in);
        }

        @Override
        public MoreInfo[] newArray(int size) {
            return new MoreInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(month, i);
        parcel.writeParcelable(year, i);
        parcel.writeParcelable(twelvemonths, i);
    }

    public class InfoData implements Parcelable {
        @SerializedName("fund")
        String fund;
        @SerializedName("CDI")
        String CDI;

        public String getFund() {
            return fund;
        }

        public String getCdi() {
            return CDI;
        }

        @Override
        public String toString() {
            return "{ fund: " + this.fund + ", CDI: " + this.CDI + " }";
        }

        protected InfoData(Parcel in) {
            fund = in.readString();
            CDI = in.readString();
        }

        public final Creator<InfoData> CREATOR = new Creator<InfoData>() {
            @Override
            public InfoData createFromParcel(Parcel in) {
                return new InfoData(in);
            }

            @Override
            public InfoData[] newArray(int size) {
                return new InfoData[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(fund);
            parcel.writeString(CDI);
        }
    }
}
