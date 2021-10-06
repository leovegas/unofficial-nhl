package com.app.unofficial_nhl.helper_classes.data_models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.evrencoskun.tableview.sort.ISortableModel;
import org.jetbrains.annotations.NotNull;

public class Cell implements ISortableModel {
    @NonNull
    private final String mId;
    @Nullable
    private final Object mData;

    public Cell(@NonNull String id, @Nullable Object data) {
        this.mId = id;
        this.mData = data;
    }

    /**
     * This is necessary for sorting process.
     * See ISortableModel
     */
    @NonNull
    @Override
    public String getId() {
        return mId;
    }

    /**
     * This is necessary for sorting process.
     * See ISortableModel
     */
    @Nullable
    @Override
    public Object getContent() {
        return mData;
    }

    @Nullable
    public Object getData() {
        return mData;
    }

}
