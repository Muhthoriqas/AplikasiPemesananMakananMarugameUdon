package com.example.marugame.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.marugame.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteAssetHelper {

    //Nama dan Versi Database
    public static final String DB_NAME="DatabaseFinal.db";
    public static final int DB_VER=1;

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //Mengambil Data Makanan
    public List<Order> getCarts()
    {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect={"ProductId","ProductName","Quantity","Price","Discount"};
        String sqlTable="OrderDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db,sqlSelect,null,null,null,null,null);

        final List<Order> result = new ArrayList<>();
        if(c.moveToFirst())
        {
            do{
                result.add(new Order(c.getString(c.getColumnIndexOrThrow("ProductId")),
                        c.getString(c.getColumnIndexOrThrow("ProductName")),
                        c.getString(c.getColumnIndexOrThrow("Quantity")),
                        c.getString(c.getColumnIndexOrThrow("Price")),
                        c.getString(c.getColumnIndexOrThrow("Discount"))
                ));
            }while(c.moveToNext());
        }
        return  result;
    }

    //Fungsi Menambahkan Pesanan ke Keranjangan
    public void addToCart(Order order)
    {
        SQLiteDatabase db= getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetail(ProductId,ProductName,Quantity,Price,Discount) VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }

    //Fungsi Menghapus Makanan
    public void clearCart()
    {
        SQLiteDatabase db= getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetail");
        db.execSQL(query);
    }

}
