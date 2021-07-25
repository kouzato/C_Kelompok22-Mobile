package com.kelompok22.veterinarycareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kelompok22.veterinarycareapp.model.DataModel;
import com.squareup.picasso.Picasso;

public class DetailArtikel extends AppCompatActivity {
    public DataModel dm;
    ImageView sampul;
    TextView judul,tanggal,isi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);
        sampul = findViewById(R.id.dt_sampul);
        judul = findViewById(R.id.dt_judul);
        isi = findViewById(R.id.dt_isi);
        tanggal = findViewById(R.id.dt_tanggal);
        getArtikel();
    }

    private void getArtikel() {
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();
        dm = gson.fromJson(data, DataModel.class);
        judul.setText(dm.getJudul());
        tanggal.setText(dm.getTanggal());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isi.setText(Html.fromHtml(dm.getIsi(), Html.FROM_HTML_MODE_LEGACY));
        } else
            isi.setText(Html.fromHtml(dm.getIsi()));
        String url = "https://wsjti.id/veterinarycare/public/img/" + dm.getFoto();
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_article)
                .error(R.drawable.ic_article)
                .into(sampul);
    }

    public void onClickBack(View view) {
        onBackPressed();
    }
}