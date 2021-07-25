package com.kelompok22.veterinarycareapp.model;

import java.util.List;

public class ListDokterResponse {
    private int kode;
    private String message;
    private List<ListDokter> data;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListDokter> getData() {
        return data;
    }

    public void setData(List<ListDokter> data) {
        this.data = data;
    }
}
