package com.matheussilas.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String DB_NAME = "DB_ASSIGNMENT";
    public static String TB_ASSIGNMENT = "assignment";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TB_ASSIGNMENT + " (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Tabela criada com Sucesso");
        } catch (Exception e) {
            Log.i("INFO DB", "Erro ao criar tabela" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
