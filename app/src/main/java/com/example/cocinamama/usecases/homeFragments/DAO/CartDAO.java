package com.example.cocinamama.usecases.homeFragments.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CartDAO {

    private DbHelper _dbHelper;

    public CartDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(Integer user_id,
                         Integer publicationdetail_id,
                         String typeorder,
                         Number price,
                         Integer amount) throws DAOException {
        Log.i("cartDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{user_id.toString(),
                                         publicationdetail_id.toString(),
                                         typeorder,
                                         price.toString(),
                                         amount.toString()};
            db.execSQL("INSERT INTO cart(" +
                    "user_id, " +
                    "publicationdetail_id," +
                    "typeorder" +
                    "price" +
                    "amount ) VALUES(?,?,?,?,?)", args);
            Log.i("CartDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("CartDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
