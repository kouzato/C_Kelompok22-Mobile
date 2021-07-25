package com.kelompok22.veterinarycareapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompok22.veterinarycareapp.R;
import com.kelompok22.veterinarycareapp.model.ListDokter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListDokterAdapter extends RecyclerView.Adapter<ListDokterAdapter.HolderData> {
    private Context context;
    private List<ListDokter> listDokter;

    public ListDokterAdapter(Context context, List<ListDokter> listDokter) {
        this.context = context;
        this.listDokter = listDokter;
    }


    @NonNull
    @Override
    public ListDokterAdapter.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_dokter, parent, false);
        ListDokterAdapter.HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDokterAdapter.HolderData holder, int position) {
        ListDokter am = listDokter.get(position);
        holder.id.setText(String.valueOf(am.getId()));
        holder.nama.setText(am.getNama_dokter());
        holder.alamat.setText(am.getAlamat_dokter());
        holder.username.setText(am.getUsername_dokter());
        holder.notelp.setText(am.getNotelp());
        holder.spesialis.setText(am.getSpesialis());
        holder.email.setText(am.getEmail_dokter());
    }

    @Override
    public int getItemCount() {
        return listDokter.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView id,nama,alamat,username,notelp,spesialis, email;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            nama = itemView.findViewById(R.id.Nama);
            alamat = itemView.findViewById(R.id.Alamat);
            username = itemView.findViewById(R.id.usernameDok);
            notelp = itemView.findViewById(R.id.NoTelp);
            spesialis = itemView.findViewById(R.id.spesialis);
            email = itemView.findViewById(R.id.emailDok);
        }
    }
}
