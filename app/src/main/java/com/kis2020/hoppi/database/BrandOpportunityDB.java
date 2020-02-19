package com.kis2020.hoppi.database;

import android.content.ContentValues;
import android.database.Cursor;

import com.kis2020.hoppi.model.BrandOpportunity;

import java.util.ArrayList;
import java.util.List;

public class BrandOpportunityDB {

    public static final String TABLE_NAME = "tbl_brand_opportunity";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_TYPE_CODE = "typeCode";
    public static final String FIELD_DISCOUNT = "discount";
    public static final String FIELD_SWEET_MONEY = "sweet_money";
    public static final String FIELD_LAST_DATE = "last_date";

    public static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" + FIELD_TITLE + " text, " + FIELD_TYPE_CODE + " text, " + FIELD_DISCOUNT + " text, " + FIELD_SWEET_MONEY + " text, " + FIELD_LAST_DATE + " text);";
    public static final String DROP_TABLE_SQL = "DROP TABLE if exists " + TABLE_NAME;


    public static List<BrandOpportunity> getAllBrandOpportunity(DatabaseHelper db) {

        Cursor cursor = db.getAllRecords(TABLE_NAME, new String[]{FIELD_TITLE, FIELD_TYPE_CODE, FIELD_DISCOUNT, FIELD_SWEET_MONEY, FIELD_LAST_DATE});
        List<BrandOpportunity> brandOpportunities = new ArrayList<>();
        BrandOpportunity brandOpportunity;

        while (cursor.moveToNext()) {
            brandOpportunity = new BrandOpportunity(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            brandOpportunities.add(brandOpportunity);
        }


        return brandOpportunities;
    }


    public static long insertBrandOpportunity(DatabaseHelper db, BrandOpportunity brandOpportunity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_TITLE, brandOpportunity.getTitle());
        contentValues.put(FIELD_TYPE_CODE, brandOpportunity.getTypeCode());
        contentValues.put(FIELD_DISCOUNT, brandOpportunity.getDiscount());
        contentValues.put(FIELD_SWEET_MONEY, brandOpportunity.getSweetMoney());
        contentValues.put(FIELD_LAST_DATE, brandOpportunity.getLastDate());

        long res = db.insert(TABLE_NAME, contentValues);
        return res;
    }

    public static boolean deleteBrandOpportunity(DatabaseHelper db, BrandOpportunity brandOpportunity) {

        String where = "title = ? and typeCode = ? and discount = ? and sweet_money = ? and last_date = ?";
        boolean res = db.delete(TABLE_NAME, where, new String[]{brandOpportunity.getTitle(), brandOpportunity.getTypeCode(), brandOpportunity.getDiscount(), brandOpportunity.getSweetMoney(), brandOpportunity.getLastDate()});
        return res;
    }


    public static boolean isBrandOpportunityExist(DatabaseHelper db, BrandOpportunity brandOpportunity) {
        String where = "title = ? and typeCode = ? and discount = ? and sweet_money = ? and last_date = ?";

        return db.isExist(TABLE_NAME, where, new String[]{brandOpportunity.getTitle(), brandOpportunity.getTypeCode(), brandOpportunity.getDiscount(), brandOpportunity.getSweetMoney(), brandOpportunity.getLastDate()});

    }
}
