package com.kelompok22.veterinarycareapp.model;

public class ListDokter {
    private int id,id_users;
    private String nama_dokter,alamat_dokter,username_dokter,notelp,spesialis,email_dokter,created_at,updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter(String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getAlamat_dokter() {
        return alamat_dokter;
    }

    public void setAlamat_dokter(String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }

    public String getUsername_dokter() {
        return username_dokter;
    }

    public void setUsername_dokter(String username_dokter) {
        this.username_dokter = username_dokter;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
            this.notelp = notelp;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getEmail_dokter() {
        return email_dokter;
    }

    public void setEmail_dokter(String email_dokter) {
        this.email_dokter = email_dokter;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
