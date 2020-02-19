package com.kis2020.hoppi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {
    private String curentBalance;
    private String membershipDate;
    private String membershipType;
    private String totalSweetMoney;

    public Account() {
    }

    public Account(String curentBalance, String membershipDate, String membershipType, String totalSweetMoney) {
        this.curentBalance = curentBalance;
        this.membershipDate = membershipDate;
        this.membershipType = membershipType;
        this.totalSweetMoney = totalSweetMoney;
    }

    public String getCurentBalance() {
        return curentBalance;
    }

    public void setCurentBalance(String curentBalance) {
        this.curentBalance = curentBalance;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getTotalSweetMoney() {
        return totalSweetMoney;
    }

    public void setTotalSweetMoney(String totalSweetMoney) {
        this.totalSweetMoney = totalSweetMoney;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(curentBalance);
        parcel.writeString(membershipDate);
        parcel.writeString(membershipType);
        parcel.writeString(totalSweetMoney);
    }


    protected Account(Parcel in) {
        curentBalance = in.readString();
        membershipDate = in.readString();
        membershipType = in.readString();
        totalSweetMoney = in.readString();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
}
