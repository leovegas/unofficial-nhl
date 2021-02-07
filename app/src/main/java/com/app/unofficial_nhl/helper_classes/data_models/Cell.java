package com.app.unofficial_nhl.helper_classes.data_models;

import androidx.annotation.Nullable;

public class Cell {
    @Nullable
    private String mData;

    public Cell(@Nullable String data) {
        this.mData = data;
    }

    @Nullable
    public String getData() {
        return mData;
    }
}
