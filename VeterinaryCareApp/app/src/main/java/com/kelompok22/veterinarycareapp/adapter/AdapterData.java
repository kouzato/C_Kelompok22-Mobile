package com.kelompok22.veterinarycareapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.kelompok22.veterinarycareapp.DetailArtikel;
import com.kelompok22.veterinarycareapp.R;
import com.kelompok22.veterinarycareapp.model.DataModel;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData> {
    private final Context ctx;
    private final List<DataModel> listArtikel;

    public AdapterData(Context ctx, List<DataModel> listArtikel){
        this.ctx = ctx;
        this.listArtikel = listArtikel;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_artikel, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listArtikel.get(position);
        holder.tvId.setText(String.valueOf(dm.getId()));
        holder.tvJudul.setText(dm.getJudul());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String url = "https://wsjti.id/veterinarycare/public/img/" + dm.getFoto();
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.ic_article)
                    .error(R.drawable.ic_article)
                    .into(holder.Sampul);
            holder.List.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ctx, DetailArtikel.class);
                    Gson gson = new Gson();
                    String str = gson.toJson(listArtikel.get(position), DataModel.class);
                    intent.putExtra("extra", str);
                    ctx.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listArtikel.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvJudul, tvId;
        ImageView Sampul;
        RelativeLayout List;

        public HolderData(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvJudul = itemView.findViewById(R.id.tv_judul);
            Sampul = itemView.findViewById(R.id.sampul);
            List = itemView.findViewById(R.id.card_artikel);
        }
    }

}
