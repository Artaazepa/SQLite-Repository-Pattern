package com.riyanto.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.riyanto.belajarsqlite.adapters.MahasiswaAdapter;
import com.riyanto.belajarsqlite.helpers.MahasiswaRepository;
import com.riyanto.belajarsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewMahasiswa;
    static ArrayList<Mahasiswa> mahasiswaList = new ArrayList<>();
    static MahasiswaAdapter adapter;
    FloatingActionButton floatingActionButton;
    static MahasiswaRepository mahasiswaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewMahasiswa = findViewById(R.id.lv_mahasiswa);
        floatingActionButton = findViewById(R.id.fab_tambah);

        mahasiswaRepository = new MahasiswaRepository(this);

        tampilMahasiswa();

        // Menambahkan OnClickListener untuk FloatingActionButton untuk menambah mahasiswa
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TambahActivity.class);
            startActivity(intent);
        });

        // Menambahkan OnItemClickListener ke ListView
        listViewMahasiswa.setOnItemClickListener((parent, view, position, id) -> {
            // Ambil mahasiswa yang diklik
            Mahasiswa mahasiswa = mahasiswaList.get(position);

            // Buat Intent dan kirim data mahasiswa sebagai Parcelable
            Intent intent = new Intent(MainActivity.this, activity_edit.class);
            intent.putExtra("mahasiswa", mahasiswa);

            // Mulai activity_edit
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

    private void tampilMahasiswa() {
        mahasiswaList = mahasiswaRepository.tampilMahasiswa();
        adapter = new MahasiswaAdapter(this, mahasiswaList);
        listViewMahasiswa.setAdapter(adapter);
    }
}
