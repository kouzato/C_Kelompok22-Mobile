package com.kelompok22.veterinarycareapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.MainActivity;
import com.kelompok22.veterinarycareapp.R;
import com.kelompok22.veterinarycareapp.model.Keluhan;
import com.kelompok22.veterinarycareapp.model.SessionManager;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText nama;
    EditText email;
    EditText isi;
    EditText foto;
    APIRequestData apiInterface;
    private String token;

    public AskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AskFragment newInstance(String param1, String param2) {
        AskFragment fragment = new AskFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nama = view.findViewById(R.id.editTextName);
        email = view.findViewById(R.id.editTextEmail);
        isi = view.findViewById(R.id.editTextKeterangan);
        foto = view.findViewById(R.id.editTextFoto);
        SessionManager sessionManager = new SessionManager(getContext());
        token = (String) sessionManager.getUserDetail().get(SessionManager.TOKEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false);
    }

    public void onClickKirim(View view) {
        apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
        Log.d("TAG", "onMasukClick: "+nama.getText());
        Log.d("TAG", "onMasukClick: "+email.getText());
        Log.d("TAG", "onMasukClick: "+isi.getText().toString());
        Log.d("TAG", "onMasukClick: "+foto.getText().toString());
        Call<Keluhan> KeluhanCall = apiInterface.keluhanResponse(nama.getText().toString(),email.getText().toString(),isi.getText().toString(), foto.getText().toString());
        KeluhanCall.enqueue(new Callback<Keluhan>() {
            @Override
            public void onResponse(Call<Keluhan> call, Response<Keluhan> response) {
                if(response.isSuccessful()){
                    nama.setText(null);
                    email.setText(null);
                    isi.setText(null);
                    foto.setText(null);
                    Toast.makeText(getContext(), "Berhasil Terkirim", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Keluhan> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
}