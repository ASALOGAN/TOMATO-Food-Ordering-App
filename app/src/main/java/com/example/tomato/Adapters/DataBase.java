package com.example.tomato.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tomato.Mdls.OrderModel;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 7;

    public DataBase(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price double," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);

    }

    public boolean insertOrder(String name, String phone, double price, int image, String desc, String foodName, int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        foodname = 5
        description = 6
        quantity = 7
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("foodname", desc);
        values.put("description", foodName);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);
        if (id <= 0){
            return false;
        }
        else {
            return true;
        }

    }
    public ArrayList<OrderModel> getOrder(){
        ArrayList<OrderModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select id, foodName, image, price from orders", null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrderModel model = new OrderModel();
                model.setOrderNumber(cursor.getInt(0) +"");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getDouble(3) +"");
                orders.add(model);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from orders where id =" + id, null);

       if (cursor != null)
           cursor.moveToFirst();

        return cursor;
    }

    public boolean updateOder(String name, String phone, double price, int image, String desc, String foodName, int quantity, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        /*
        id = 0
        name = 1
        phone = 2
        price = 3
        image = 4
        foodname = 5
        description = 6
        quantity = 7
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("foodname", desc);
        values.put("description", foodName);
        values.put("quantity", quantity);
        long row = database.update("orders", values, "id="+id, null);
        if (row <= 0){
            return false;
        }
        else {
            return true;
        }

    }

    public int deleteOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id="+id, null);
    }
}
