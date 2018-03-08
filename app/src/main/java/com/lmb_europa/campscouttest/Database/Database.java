package com.lmb_europa.campscouttest.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.lmb_europa.campscouttest.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AleksandraPC on 28-Feb-18.
 */

public class Database extends SQLiteAssetHelper {

    private static final String DB_NAME = "CampScoutDB.db";
    private static final int DB_VER = 1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"SpotNum", "SpotId", "Price", "Discount"};
        String sqlTable = "ReserveDetails";
        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);

        final List<Order> result = new ArrayList<>();
        if (cursor.moveToFirst())
        {
            do {
                result.add(new Order(cursor.getString(cursor.getColumnIndex("SpotId")),
                        cursor.getString(cursor.getColumnIndex("SpotNum")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))));
            }while (cursor.moveToNext());
        }
        return result;
    }

    public void addToCart(Order order)
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO ReserveDetails(SpotId, SpotNum, Price, Discount) VALUES('%s','%s','%s','%s');",
                order.getSpotId(),
                order.getSpotNum(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }

    public void cleanCart()
    {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM ReserveDetails");
        db.execSQL(query);
    }
}
