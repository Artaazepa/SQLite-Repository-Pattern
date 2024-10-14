package com.riyanto.belajarsqlite.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.riyanto.belajarsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaRepository {

    private static DatabaseHelper databaseHelper;

    public MahasiswaRepository(Context context) {
        databaseHelper = Singleton.getInstance(context);
    }

    public ArrayList<Mahasiswa> tampilMahasiswa() {
        ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM mahasiswa", null);

        while (cursor.moveToNext()) {
            mahasiswaList.add(
                new Mahasiswa(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
            );
        }
        return mahasiswaList;
    }

    public void tambahMahasiswa(Mahasiswa m) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = String.format("INSERT INTO mahasiswa (nim, nama, prodi) VALUES ('%s', '%s', '%s')", m.getNim(), m.getNama(), m.getProdi());
        db.execSQL(sql);
    }

    public void ubahMahasiswa(String nimLama, Mahasiswa mahasiswaBaru) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nim", mahasiswaBaru.getNim());
        values.put("nama", mahasiswaBaru.getNama());
        values.put("prodi", mahasiswaBaru.getProdi());

        // Update data di database berdasarkan nim lama
        db.update("mahasiswa", values, "nim = ?", new String[]{nimLama});
    }


    public void hapusMahasiswa(String nim) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        String sql = String.format("DELETE FROM mahasiswa WHERE nim = '%s'", nim);
        db.execSQL(sql);
    }
}
