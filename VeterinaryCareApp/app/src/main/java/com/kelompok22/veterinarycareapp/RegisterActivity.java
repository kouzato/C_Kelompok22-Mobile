package com.kelompok22.veterinarycareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.model.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMovetoLogin, btnRegister;
    private EditText etName, etUser, etEmail, etPass, etConfPass;
    APIRequestData apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        etName = findViewById(R.id.editText);
        etUser = findViewById(R.id.editText2);
        etEmail = findViewById(R.id.editText3);
        etPass = findViewById(R.id.editText4);
        etConfPass = findViewById(R.id.editText5);


        btnMovetoLogin = findViewById(R.id.login_button);
        btnMovetoLogin.setOnClickListener((View.OnClickListener) this);

        btnRegister = findViewById(R.id.register_button);
        btnRegister.setOnClickListener((View.OnClickListener) this);


    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                onBackPressed();
                break;
            case R.id.register_button:
                apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
                Log.d("TAG", "onMasukClick: "+etName.getText().toString());
                Log.d("TAG", "onMasukClick: "+etUser.getText().toString());
                Log.d("TAG", "onMasukClick: "+etEmail.getText().toString());
                Log.d("TAG", "onMasukClick: "+etPass.getText().toString());
                Log.d("TAG", "onMasukClick: "+etConfPass.getText().toString());
                Call<Register> registerCall = apiInterface.registerresponse(etName.getText().toString(),etUser.getText().toString(),etEmail.getText().toString(),etPass.getText().toString(),etConfPass.getText().toString());
                registerCall.enqueue(new Callback<Register>() {
                    @Override
                    public void onResponse(Call<Register> call, Response<Register> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        }else {
                            Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.d("onResponse", "onResponse: " + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<Register> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", "onFailure: " + t.getMessage());
                    }
                });
                break;
        }
    }

}