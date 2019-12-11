package com.example.timeline.helper;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="event.db";
    private static final int DATABASE_VERSION=1;

    public DataHelper (Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE kegiatan (nomor INTEGER, event TEXT, tanggal TEXT, keterangan TEXT, waktu_mulai TEXT, waktu_akhir TEXT);";
        db.execSQL(sql);
        sql = "INSERT INTO kegiatan (nomor, event,tanggal, keterangan, waktu_mulai, waktu_akhir) values('1','event','01/01/2020','dresscode etc,', '00:00', '01.00');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
