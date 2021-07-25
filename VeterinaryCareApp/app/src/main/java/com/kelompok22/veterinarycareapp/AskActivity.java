package com.kelompok22.veterinarycareapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kelompok22.veterinarycareapp.API.APIRequestData;
import com.kelompok22.veterinarycareapp.API.RetroServer;
import com.kelompok22.veterinarycareapp.R;
import com.kelompok22.veterinarycareapp.model.Keluhan;
import com.kelompok22.veterinarycareapp.model.KeluhanResponse;
import com.kelompok22.veterinarycareapp.model.ListKeluhanModel;
import com.kelompok22.veterinarycareapp.model.Register;
import com.kelompok22.veterinarycareapp.model.SessionManager;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AskActivity extends AppCompatActivity {

    EditText nama;
    EditText email;
    EditText isi;
    EditText foto;
    Button Bfoto;
    APIRequestData apiInterface;
    private String token;
    Button selectIMG;
    final int SELECT_PICTURE = 9544;
    ListKeluhanModel lm;
    private Uri selectedImageUri;
    String part_image;
    ImageView imageView;
    private static final int MY_PERMISSION_REQUEST = 100;
    private final int PICK_IMAGE_FROM_GALERY_REQUEST = 1;
    static final int REQUEST_IMAGE_CAPTURE = 0;

    private File file;
    private final String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);

        nama = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        isi = findViewById(R.id.editTextKeterangan);
        foto = findViewById(R.id.editTextFoto);
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();

        lm = gson.fromJson(data, ListKeluhanModel.class);
        Button konfirmasi;
        konfirmasi = findViewById(R.id.kirimKonfirmasi);
        selectIMG = findViewById(R.id.ButtonFoto);
        imageView = findViewById(R.id.imageView);
        SessionManager sessionManager = new SessionManager(getBaseContext());
        token = (String) sessionManager.getUserDetail().get(SessionManager.TOKEN);
        if (ContextCompat.checkSelfPermission(AskActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(AskActivity.this, new String[] {Manifest.permission.CAMERA}, MY_PERMISSION_REQUEST);
        }

        if (ContextCompat.checkSelfPermission(AskActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AskActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
        }
        selectIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage(AskActivity.this);
            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File imageFile = new File(part_image);
//                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
//                MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

                apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
                Log.d("TAG", "onMasukClick: " + nama.getText().toString());
                Log.d("TAG", "onMasukClick: " + email.getText().toString());
                Log.d("TAG", "onMasukClick: " + isi.getText().toString());
//                Log.d("TAG", "onMasukClick: " + imageFile.getName());
                Call<KeluhanResponse> keluhanResponseCall = apiInterface.Keluhan(nama.getText().toString(), email.getText().toString(), isi.getText().toString());
                keluhanResponseCall.enqueue(new Callback<KeluhanResponse>() {
                    @Override
                    public void onResponse(Call<KeluhanResponse> call, Response<KeluhanResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(AskActivity.this, "Berhasil Mengirim Keluhan", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AskActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(AskActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                            Log.d("onResponse", "onResponse: " + response.message());
                        }

                    }

                    @Override
                    public void onFailure(Call<KeluhanResponse> call, Throwable t) {
                        Toast.makeText(AskActivity.this, "Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }


    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent m_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
                    startActivityForResult(m_intent, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    pickPhoto.setType("image/*");
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public String getPackageName(Context context) {
        return context.getPackageName();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 || requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (resultCode != RESULT_CANCELED) {
                switch (requestCode) {
                    case 0:
                        if (resultCode == RESULT_OK && data != null) {
                            File file = new File(Environment.getExternalStorageDirectory(), "MyPhoto.jpg");
                            //Uri of camera image
                            Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", file);
                            selectedImageUri = uri;
                            String[] imageprojection1 = {MediaStore.Images.Media.DATA};
                            Cursor cursor1 = getContentResolver().query(selectedImageUri, imageprojection1, null, null, null);
                            if (cursor1 != null) {
                                cursor1.moveToFirst();
                                int indexImage = cursor1.getColumnIndex(imageprojection1[0]);
                                part_image = cursor1.getString(indexImage);
                                if (part_image != null) {
                                    file = new File(part_image);
                                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                                }
                            }
                        }

                        break;
                    case 1:
                        if (resultCode == RESULT_OK && data != null) {
                            selectedImageUri = data.getData();
                            String[] imageprojection2 = {MediaStore.Images.Media.DATA};
                            Cursor cursor2 = getContentResolver().query(selectedImageUri, imageprojection2, null, null, null);
                            if (cursor2 != null) {
                                cursor2.moveToFirst();
                                int indexImage = cursor2.getColumnIndex(imageprojection2[0]);
                                part_image = cursor2.getString(indexImage);
                                if (part_image != null) {
                                    file = new File(part_image);
                                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.getPath()));
                                }
                            }

                        }
                        break;
                }
            }
        }
    }
}