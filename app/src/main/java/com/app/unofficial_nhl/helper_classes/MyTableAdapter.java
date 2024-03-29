package com.app.unofficial_nhl.helper_classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.app.unofficial_nhl.helper_classes.data_models.ColumnHeader;
import com.app.unofficial_nhl.helper_classes.data_models.RowHeader;
import com.app.unofficial_nhl.helper_classes.holder.CellViewHolder;
import com.app.unofficial_nhl.helper_classes.holder.ColumnHeaderViewHolder;
import com.app.unofficial_nhl.helper_classes.holder.RowHeaderViewHolder;
import com.evrencoskun.tableview.adapter.AbstractTableAdapter;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class MyTableAdapter extends AbstractTableAdapter<ColumnHeader, RowHeader,
        Cell> {

    private ColumnHeader first;

    private MyTableViewModel myTableViewModel;

    public ColumnHeader getFirst() {
        return first;
    }


    public MyTableAdapter() {
        super();
        this.myTableViewModel = new MyTableViewModel();
        this.first = this.getColumnHeaderItem(0);
    }



    @Override
    public AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType) {
        View layout;


                // Get default Cell xml Layout
                layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_cell_layout,
                        parent, false);

                // Create a Cell ViewHolder
                return new CellViewHolder(layout);

    }

    @Override
    public void onBindCellViewHolder(@NonNull AbstractViewHolder holder, @Nullable  Cell cellItemModel, int columnPosition, int rowPosition) {

        Cell cell = (Cell) cellItemModel;
//        CellViewHolder viewHolder = (CellViewHolder) holder;
//
//        viewHolder.setCellModel(cell, columnPosition);

        CellViewHolder viewHolder = (CellViewHolder) holder;
        viewHolder.setCellModel(cellItemModel, columnPosition);

//        // Get the holder to update cell item text
        CellViewHolder holder1 = (CellViewHolder) holder;
        holder1.cell_textview.setText(cell.getId());
        if (columnPosition==1)
        holder1.itemView.performClick();
//
//        // If your TableView should have auto resize for cells & columns.
//        // Then you should consider the below lines. Otherwise, you can ignore them.
//
//        // It is necessary to remeasure itself.
//        viewHolder.cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        viewHolder.cell_textview.requestLayout();
    }

    @Override
    public AbstractSorterViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .table_view_column_header_layout, parent, false);

        return new ColumnHeaderViewHolder(layout, getTableView());
    }

    @Override
    public void onBindColumnHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable ColumnHeader columnHeaderItemModel, int columnPosition) {
            ColumnHeader columnHeader = (ColumnHeader) columnHeaderItemModel;

            // Get the holder to update cell item text
            ColumnHeaderViewHolder columnHeaderViewHolder = (ColumnHeaderViewHolder) holder;

            columnHeaderViewHolder.setColumnHeaderModel(columnHeader, columnPosition);
    }



    @Override
    public AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType) {

        // Get Row Header xml Layout
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_row_header_layout,
                parent, false);

        // Create a Row Header ViewHolder
        return new RowHeaderViewHolder(layout);
    }

    @Override
    public void onBindRowHeaderViewHolder(@NonNull AbstractViewHolder holder, @Nullable RowHeader rowHeaderItemModel, int rowPosition) {
        RowHeader rowHeaderModel = (RowHeader) rowHeaderItemModel;

        RowHeaderViewHolder rowHeaderViewHolder = (RowHeaderViewHolder) holder;
        rowHeaderViewHolder.row_header_textview.setText((CharSequence) rowHeaderModel.getContent());
    }

    @Override
    public View onCreateCornerView(ViewGroup parent) {
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.table_view_corner_layout, null, false);
    }

    @Override
    public int getColumnHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getRowHeaderItemViewType(int position) {
        return 0;
    }

    @Override
    public int getCellItemViewType(int position) {
        return myTableViewModel.getCellItemViewType(position);
    }



}
