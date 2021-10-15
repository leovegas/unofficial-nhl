package com.app.unofficial_nhl.ui.cardview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.helper_classes.TinyDB;

import java.util.List;

public class CardViewAdapterTeams extends RecyclerView.Adapter<CardViewAdapterTeams.StockViewHolder> {

    List<Integer> logos;
    List<String> shortnames;

    public CardViewAdapterTeams(List<Integer> logos, List<String> shortnames) {
        this.logos = logos;
        this.shortnames = shortnames;
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_teams, parent, false);
        StockViewHolder pvh = new StockViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        TinyDB tinydb = new TinyDB(holder.cv.getContext());
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(1000);
        holder.cv.setAlpha(1f);
        holder.cv.startAnimation(animation1);
        holder.logo.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(shortnames.get(position)))));
        holder.shortname.setText(shortnames.get(position));
        int idMarked = tinydb.getInt(shortnames.get(position));
        if (idMarked!=0) {
            holder.reminder.setVisibility(View.VISIBLE);
        }



/*        ImageView goal = new ImageView(holder.itemView.getContext());
        goal.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(scoredteams.get(position)))));

        goal.getLayoutParams().width= RelativeLayout.LayoutParams.WRAP_CONTENT;
        goal.getLayoutParams().height=RelativeLayout.LayoutParams.WRAP_CONTENT;
        holder.frameLay.addView(goal);*/

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return logos.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView shortname;
        TextView stockPrice;
        ImageView logo;
        ImageView field;
        ImageView reminder;

        StockViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            shortname = (TextView) itemView.findViewById(R.id.shortname);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            reminder = (ImageView) itemView.findViewById(R.id.reminder);
        }
    }


}