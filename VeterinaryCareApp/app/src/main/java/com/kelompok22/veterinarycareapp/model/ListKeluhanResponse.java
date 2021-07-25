package com.kelompok22.veterinarycareapp.model;

import java.util.List;

public class ListKeluhanResponse {
    private int code;
    private String message;
    private List<ListKeluhanModel> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ListKeluhanModel> getData() {
        return data;
    }

    public void setData(List<ListKeluhanModel> data) {
        this.data = data;
    }
}
