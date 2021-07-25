package com.kelompok22.veterinarycareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.adapter.AdapterData;
import com.kelompok22.veterinarycareapp.adapter.ListDokterAdapter;
import com.kelompok22.veterinarycareapp.model.DataModel;
import com.kelompok22.veterinarycareapp.model.ListDokter;
import com.kelompok22.veterinarycareapp.model.ListDokterResponse;
import com.kelompok22.veterinarycareapp.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDokterActivity extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<ListDokter> listData = new ArrayList<>();
    private TextView Hasil;
    private AdapterData ListDokterAdapter;
    private SearchView searchView;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dokter);
        rvData = findViewById(R.id.lisDok);
        recyclerView = findViewById(R.id.lisDok);
        rvData.setLayoutManager(new LinearLayoutManager(ListDokterActivity.this, LinearLayoutManager.VERTICAL, false));
        getDokter();
    }

    private void getDokter() {
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ListDokterResponse> tampilData = ardData.listdokter();

        tampilData.enqueue(new Callback<ListDokterResponse>() {
            @Override
            public void onResponse(Call<ListDokterResponse> call, Response<ListDokterResponse> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getMessage();
                listData = response.body().getData();

                adData = new ListDokterAdapter(ListDokterActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ListDokterResponse> call, Throwable t) {
                Toast.makeText(ListDokterActivity.this, "Failed to Contact Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onClickBack(View view) {
        onBackPressed();
    }
}