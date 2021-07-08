package com.kelompok22.veterinarycareapp;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnMovetoLogin, btnRegister;
    private EditText etName, etUser, etEmail, etPass, etConfPass;
    private TextView tvStatus;
    private String URL = "http://192.168.1.18/veterinary-care-app/register.php";
    private String name, username, email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.editText);
        etUser = findViewById(R.id.editText2);
        etEmail = findViewById(R.id.editText3);
        etPass = findViewById(R.id.editText4);
        etConfPass = findViewById(R.id.editText5);
        tvStatus = findViewById(R.id.tvStatus);
        name = username = email = password = confirmPassword = "";


        btnMovetoLogin = findViewById(R.id.login_button);
        btnMovetoLogin.setOnClickListener((View.OnClickListener) this);

        btnRegister = findViewById(R.id.register_button);
        btnRegister.setOnClickListener((View.OnClickListener) this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                onBackPressed();
                break;
            case R.id.register_button:
                name = etName.getText().toString().trim();
                username = etUser.getText().toString().trim();
                email = etEmail.getText().toString().trim();
                password = etPass.getText().toString().trim();
                confirmPassword = etConfPass.getText().toString().trim();
                if (!password.equals(confirmPassword)){
                    Toast.makeText(this, "Confirm Password harus sama dengan Password", Toast.LENGTH_SHORT).show();
                }else if (!name.equals("") && !email.equals("") && !password.equals("")){
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("sukses")) {
                                tvStatus.setText("Registrasi Sukses");
                                btnRegister.setClickable(false);
                                onBackPressed();
                            } else if (response.equals("fail")) {
                                Toast.makeText(RegisterActivity.this, "Terjadi Kesalahan!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> data = new HashMap<>();
                            data.put("name", name);
                            data.put("username", username);
                            data.put("email", email);
                            data.put("password", password);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }
                break;
        }
    }
}