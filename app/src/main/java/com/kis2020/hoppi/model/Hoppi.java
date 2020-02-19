package com.kis2020.hoppi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Hoppi implements Parcelable {


    private Account account;

    private List<BrandOpportunity> brandOpportunities;

    private List<Transaction> transactions;

    public Hoppi() {
    }

    public Hoppi(Account account, List<BrandOpportunity> brandOpportunities, List<Transaction> transactions) {
        this.account = account;
        this.brandOpportunities = brandOpportunities;
        this.transactions = transactions;
    }

    protected Hoppi(Parcel in) {
        account = in.readParcelable(Account.class.getClassLoader());
        brandOpportunities = in.createTypedArrayList(BrandOpportunity.CREATOR);
        transactions = in.createTypedArrayList(Transaction.CREATOR);
    }

    public static final Creator<Hoppi> CREATOR = new Creator<Hoppi>() {
        @Override
        public Hoppi createFromParcel(Parcel in) {
            return new Hoppi(in);
        }

        @Override
        public Hoppi[] newArray(int size) {
            return new Hoppi[size];
        }
    };

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<BrandOpportunity> getBrandOpportunities() {
        return brandOpportunities;
    }

    public void setBrandOpportunities(List<BrandOpportunity> brandOpportunities) {
        this.brandOpportunities = brandOpportunities;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(account, i);
        parcel.writeTypedList(brandOpportunities);
        parcel.writeTypedList(transactions);
    }
}
