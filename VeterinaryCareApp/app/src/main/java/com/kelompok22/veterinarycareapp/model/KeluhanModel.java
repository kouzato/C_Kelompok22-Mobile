package com.kelompok22.veterinarycareapp.model;

import com.android.volley.toolbox.StringRequest;

public class KeluhanModel {
    private int id;
    private String nama,email,isi,foto;

    public KeluhanModel(int id, String nama, String email, String isi, String foto) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.isi = isi;
        this.foto = foto;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

}
