package com.kis2020.hoppi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BrandOpportunity implements Parcelable {

    private String title;

    private String typeCode;

    private String discount;

    private String sweetMoney;

    private String lastDate;

    public BrandOpportunity() {
    }

    public BrandOpportunity(String title, String typeCode, String discount, String sweetMoney, String lastDate) {
        this.title = title;
        this.typeCode = typeCode;
        this.discount = discount;
        this.sweetMoney = sweetMoney;
        this.lastDate = lastDate;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getSweetMoney() {
        return sweetMoney;
    }

    public void setSweetMoney(String sweetMoney) {
        this.sweetMoney = sweetMoney;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(typeCode);
        parcel.writeString(discount);
        parcel.writeString(sweetMoney);
        parcel.writeString(lastDate);
    }

    protected BrandOpportunity(Parcel in) {
        title = in.readString();
        typeCode = in.readString();
        discount = in.readString();
        sweetMoney = in.readString();
        lastDate = in.readString();
    }

    public static final Creator<BrandOpportunity> CREATOR = new Creator<BrandOpportunity>() {
        @Override
        public BrandOpportunity createFromParcel(Parcel in) {
            return new BrandOpportunity(in);
        }

        @Override
        public BrandOpportunity[] newArray(int size) {
            return new BrandOpportunity[size];
        }
    };
}
