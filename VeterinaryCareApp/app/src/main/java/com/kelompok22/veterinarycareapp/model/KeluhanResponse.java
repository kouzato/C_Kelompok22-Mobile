package com.kelompok22.veterinarycareapp.model;

import com.google.gson.annotations.SerializedName;

public class KeluhanResponse {
    @SerializedName("keluhan")
    private KeluhanModel keluhanModel;

    public KeluhanModel getKeluhanModel() {
        return keluhanModel;
    }

    public void setKeluhanModel(KeluhanModel keluhanModel) {
        this.keluhanModel = keluhanModel;
    }
}