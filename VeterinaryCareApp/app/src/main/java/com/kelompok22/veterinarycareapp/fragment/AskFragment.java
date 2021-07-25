package com.kelompok22.veterinarycareapp.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.AskActivity;
import com.kelompok22.veterinarycareapp.LoginActivity;
import com.kelompok22.veterinarycareapp.MainActivity;
import com.kelompok22.veterinarycareapp.R;
import com.kelompok22.veterinarycareapp.RegisterActivity;
import com.kelompok22.veterinarycareapp.TentangKami;
import com.kelompok22.veterinarycareapp.model.Keluhan;
import com.kelompok22.veterinarycareapp.model.Login;
import com.kelompok22.veterinarycareapp.model.LoginData;
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
    private Button btnTanya, btnListTanya;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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


        btnTanya = view.findViewById(R.id.Tanya);
        btnTanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AskActivity.class);
                startActivity(intent);
            }
        });
        btnListTanya = view.findViewById(R.id.ListTanya);
        btnListTanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AskActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ask, container, false);
    }


}