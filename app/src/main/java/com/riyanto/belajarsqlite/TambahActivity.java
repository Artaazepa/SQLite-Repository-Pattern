package com.riyanto.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.riyanto.belajarsqlite.helpers.DatabaseHelper;
import com.riyanto.belajarsqlite.helpers.MahasiswaRepository;
import com.riyanto.belajarsqlite.helpers.Singleton;
import com.riyanto.belajarsqlite.models.Mahasiswa;

public class TambahActivity extends AppCompatActivity {

    EditText etNim, etNama;
    Spinner spProdi;
    Button btnSimpan;
    MahasiswaRepository mahasiswaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        spProdi = findViewById(R.id.sp_prodi);
        btnSimpan = findViewById(R.id.btn_simpan);

        mahasiswaRepository = new MahasiswaRepository(this);

        String[] arrProdi = {"Manajemen Informatika", "Teknik Listrik"};
        spProdi.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrProdi
        ));

        btnSimpan.setOnClickListener(v -> {
            String nim = etNim.getText().toString();
            String nama = etNama.getText().toString();
            String prodi = spProdi.getSelectedItem().toString();

            mahasiswaRepository.tambahMahasiswa(new Mahasiswa(nim, nama, prodi));

            super.onBackPressed();
        });
    }
}