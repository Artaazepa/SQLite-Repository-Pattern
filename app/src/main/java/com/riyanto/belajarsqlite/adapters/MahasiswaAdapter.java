package com.riyanto.belajarsqlite.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.riyanto.belajarsqlite.R;
import com.riyanto.belajarsqlite.models.Mahasiswa;

import java.util.ArrayList;

public class MahasiswaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Mahasiswa> mahasiswaList;

    public MahasiswaAdapter(Context context, ArrayList<Mahasiswa> mahasiswaList) {
        this.context = context;
        this.mahasiswaList = mahasiswaList;
    }

    @Override
    public int getCount() {
        return mahasiswaList.size();
    }

    @Override
    public Object getItem(int position) {
        return mahasiswaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);

        TextView tvNama = view.findViewById(R.id.tv_nama);
        TextView tvNim = view.findViewById(R.id.tv_nim);
        TextView tvProdi = view.findViewById(R.id.tv_prodi);

        tvNama.setText(mahasiswaList.get(position).getNama());
        tvNim.setText(mahasiswaList.get(position).getNim());
        tvProdi.setText(mahasiswaList.get(position).getProdi());

        return view;
    }
}
