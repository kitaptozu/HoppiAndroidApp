package com.kis2020.hoppi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable {

    private String name;

    private String type;

    private String date;

    private String amount;

    public Transaction() {
    }

    public Transaction(String name, String type, String date, String amount) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeString(date);
        parcel.writeString(amount);
    }

    protected Transaction(Parcel in) {
        name = in.readString();
        type = in.readString();
        date = in.readString();
        amount = in.readString();
    }
    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };
}
