package com.riyanto.belajarsqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.riyanto.belajarsqlite.helpers.MahasiswaRepository;
import com.riyanto.belajarsqlite.models.Mahasiswa;

public class activity_edit extends AppCompatActivity {

    EditText etNim, etNama;
    Spinner spProdi;
    Button btnUbah, btnHapus;
    MahasiswaRepository mahasiswaRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Inisialisasi view dan repository
        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        spProdi = findViewById(R.id.sp_prodi);
        btnUbah = findViewById(R.id.btn_ubah);
        btnHapus = findViewById(R.id.btn_hapus);
        mahasiswaRepository = new MahasiswaRepository(this);

        // Ambil data dari Intent
        Mahasiswa mhs = getIntent().getParcelableExtra("mahasiswa");

        // Isi form dengan data yang diterima
            etNim.setText(mhs.getNim());
            etNama.setText(mhs.getNama());


        // Array prodi sama seperti TambahActivity
        String[] arrProdi = {"Manajemen Informatika", "Teknik Listrik"};
        ArrayAdapter<String> adapterProdi = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrProdi);
        spProdi.setAdapter(adapterProdi);

        // Set Spinner ke nilai yang sesuai dengan data dari Intent
            String prodi = mhs.getProdi();
            for (int i = 0; i < arrProdi.length; i++) {
                if (arrProdi[i].equals(prodi)) {
                    spProdi.setSelection(i);  // Pilih item sesuai data dari Intent
                    break;

            }
        }

        // Ketika tombol Ubah ditekan (untuk mengubah data)
        btnUbah.setOnClickListener(v -> {
            Mahasiswa mahasiswa = new Mahasiswa(
                    etNim.getText().toString(),  // nim yang diubah
                    etNama.getText().toString(),
                    spProdi.getSelectedItem().toString()  // Mendapatkan item terpilih dari Spinner
            );

            // Memperbarui data mahasiswa di database
                mahasiswaRepository.ubahMahasiswa(mhs.getNim(), mahasiswa);  // nim lama digunakan untuk referensi


            Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();

            // Kembali ke MainActivity setelah mengubah data
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });

        // Ketika tombol Hapus ditekan (untuk menghapus data)
        btnHapus.setOnClickListener(v -> {
                mahasiswaRepository.hapusMahasiswa(mhs.getNim());

            Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();

            // Kembali ke MainActivity setelah menghapus data
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
