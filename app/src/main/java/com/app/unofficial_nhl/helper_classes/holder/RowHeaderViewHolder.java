package com.app.unofficial_nhl.helper_classes.holder;

import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.app.unofficial_nhl.R;
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created by evrencoskun on 1.12.2017.
 */

public class RowHeaderViewHolder extends AbstractViewHolder {
    public final TextView row_header_textview;

    public RowHeaderViewHolder(View p_jItemView) {
        super(p_jItemView);
        row_header_textview = p_jItemView.findViewById(R.id.row_header_textView);
    }

    @Override
    public void setSelected(SelectionState p_nSelectionState) {
        super.setSelected(p_nSelectionState);

        int nBackgroundColorId;
        int nForegroundColorId;

        if (p_nSelectionState == SelectionState.SELECTED) {
            nBackgroundColorId = R.color.primaryapp;
            nForegroundColorId = R.color.textlesswhite;

        } else if (p_nSelectionState == SelectionState.UNSELECTED) {
            nBackgroundColorId = R.color.primaryapp;
            nForegroundColorId = R.color.textlesswhite;

        } else { // SelectionState.SHADOWED

            nBackgroundColorId = R.color.primaryapp;
            nForegroundColorId = R.color.textlesswhite;
        }

        itemView.setBackgroundColor(ContextCompat.getColor(itemView.getContext(),
                nBackgroundColorId));
        row_header_textview.setTextColor(ContextCompat.getColor(row_header_textview.getContext(),
                nForegroundColorId));
    }
}
