package com.example.iot;

/**
 * Created by arpitkh996 on 12-03-2016.
 */
import android.os.Parcel;
import android.os.Parcelable;


import java.io.Serializable;


/**
 * Created by arpitkh996 on 12-02-2016.
 */
public class Data implements Parcelable,Serializable {
    String t;
    String link;


    public Data(String t, String link) {
        this.t = t;
        this.link = link;
    }


    protected Data(Parcel in) {
        t = in.readString();
        link = in.readString();
    }


    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }


        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };


    public String getTitle() {
        return t;
    }


    public String getLink() {
        return link;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;


        Data data = (Data) o;


        if (t != null ? !t.equals(data.t) : data.t != null) return false;
        return link != null ? link.equals(data.link) : data.link == null;


    }


    @Override
    public int hashCode() {
        int result = t != null ? t.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(t);
        parcel.writeString(link);
    }
}