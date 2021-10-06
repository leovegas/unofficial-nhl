package com.app.unofficial_nhl.helper_classes.holder;

import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.data_models.Cell;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

public class CellViewHolder extends AbstractViewHolder {
//    public final LinearLayout cell_container;
//    public final TextView cell_textview;
//
//    public CellViewHolder(View itemView) {
//        super(itemView);
//        cell_container = itemView.findViewById(R.id.cell_container);
//        cell_textview = itemView.findViewById(R.id.cell_data);
//

    public final TextView cell_textview;
    public final LinearLayout cell_container;

    public CellViewHolder(View itemView) {
        super(itemView);
        cell_textview = itemView.findViewById(R.id.cell_data);
        cell_container = itemView.findViewById(R.id.cell_container);
    }

    public void setCellModel(Cell p_jModel, int pColumnPosition) {

        // Change textView align by column
        cell_textview.setGravity(ColumnHeaderViewHolder.COLUMN_TEXT_ALIGNS[pColumnPosition] |
                Gravity.CENTER_VERTICAL);

        // Set text
        cell_textview.setText(String.valueOf(p_jModel.getData()));

        // It is necessary to remeasure itself.
        cell_container.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
        cell_textview.requestLayout();
    }

    @Override
    public void setSelected(SelectionState p_nSelectionState) {
        super.setSelected(p_nSelectionState);

        if (p_nSelectionState == SelectionState.SELECTED) {
            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color
                    .primaryapp));
        } else {
            cell_textview.setTextColor(ContextCompat.getColor(cell_textview.getContext(), R.color
                    .textlesswhite));
        }
    }

}