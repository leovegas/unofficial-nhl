package com.app.unofficial_nhl.helper_classes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.holder.ColumnHeaderViewHolder;
import com.evrencoskun.tableview.ITableView;
import com.evrencoskun.tableview.listener.ITableViewListener;

public class MyTableViewListener implements ITableViewListener {
    private static final String LOG_TAG = MyTableViewListener.class.getSimpleName();
    @NonNull
    private ITableView mTableView;
    @NonNull
    private final Context mContext;

    public MyTableViewListener(ITableView mTableView) {
        this.mContext = mTableView.getContext();
        this.mTableView = mTableView;
    }

    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

        Log.d(LOG_TAG, "onCellClicked has been clicked for x= " + column + " y= " + row);
    }

    @Override
    public void onCellDoubleClicked(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder cellView, int column, int row) {

    }

    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

        Log.d(LOG_TAG, "onCellLongPressed has been clicked for " + row);
    }

    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {
        if (columnHeaderView != null && columnHeaderView instanceof ColumnHeaderViewHolder) {

            // Create Long Press Popup
            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) columnHeaderView, mTableView);

            // Show
            popup.show();
        }
        Log.d(LOG_TAG, "onColumnHeaderClicked has been clicked for " + column);
    }

    @Override
    public void onColumnHeaderDoubleClicked(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder columnHeaderView, int column) {


    }

    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {

/*        if (columnHeaderView != null && columnHeaderView instanceof ColumnHeaderViewHolder) {

            // Create Long Press Popup
            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) columnHeaderView, mTableView);

            // Show
            popup.show();
        }*/
    }

    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        Log.d(LOG_TAG, "onRowHeaderClicked has been clicked for " + row);
    }

    @Override
    public void onRowHeaderDoubleClicked(@NonNull @org.jetbrains.annotations.NotNull RecyclerView.ViewHolder rowHeaderView, int row) {

    }

    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder owHeaderView, int row) {
        Log.d(LOG_TAG, "onRowHeaderLongPressed has been clicked for " + row);
    }
}