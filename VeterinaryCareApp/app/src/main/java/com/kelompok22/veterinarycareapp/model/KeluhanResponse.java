package com.kelompok22.veterinarycareapp.model;

import com.google.gson.annotations.SerializedName;

public class KeluhanResponse {

    @SerializedName("user")
    private KeluhanModel keluhanModel;

    @SerializedName("token")
    private String token;

    public void setKeluhanModel(KeluhanModel keluhanModel){
        this.keluhanModel = keluhanModel;
    }

    public KeluhanModel getKeluhanModel(){
        return keluhanModel;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}