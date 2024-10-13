package com.riyanto.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.riyanto.belajarsqlite.adapters.MahasiswaAdapter;
import com.riyanto.belajarsqlite.helpers.DatabaseHelper;
import com.riyanto.belajarsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMahasiswa;
    static DatabaseHelper databaseHelper;
    static ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    MahasiswaAdapter adapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMahasiswa    = findViewById(R.id.lv_mahasiswa);
        floatingActionButton = findViewById(R.id.fab_tambah);

        databaseHelper = new DatabaseHelper(this);

        tampilMahasiswa();
        adapter = new MahasiswaAdapter(this, mahasiswaList);

        listViewMahasiswa.setAdapter(adapter);

        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TambahActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mahasiswaList.clear();
        tampilMahasiswa();
        adapter.notifyDataSetChanged();
    }

    private static void tampilMahasiswa() {
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
    }
}