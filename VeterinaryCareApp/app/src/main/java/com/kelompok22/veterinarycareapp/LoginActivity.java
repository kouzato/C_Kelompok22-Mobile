package com.kelompok22.veterinarycareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.model.Login;
import com.kelompok22.veterinarycareapp.model.LoginData;
import com.kelompok22.veterinarycareapp.model.SessionManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUser, etPass;
    String username, password;
    APIRequestData apiInterface;
    SessionManager sessionManager;
    private Button btnMovetoRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);

        btnMovetoRegister = findViewById(R.id.register);
        btnMovetoRegister.setOnClickListener(this);

        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.login:
                apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
                Log.d("TAG", "onMasukClick: "+etUser.getText().toString());
                Call<Login> loginCall = apiInterface.loginresponse(etUser.getText().toString(),etPass.getText().toString());
                loginCall.enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                            sessionManager = new SessionManager(LoginActivity.this);
                            LoginData loginData = response.body().getLoginData();
                            String login = response.body().getToken();
                            sessionManager.createLoginSession(loginData,login);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            Log.d("onResponse", "onResponse: " + login);
                        }else {
                            Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.d("onResponse", "onResponse: " + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", "onFailure: " + t.getMessage());
                    }
                });
                break;
        }
    }
}