package com.app.unofficial_nhl.ui.cardview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.FullscreenActivity;
import com.app.unofficial_nhl.MainActivity2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Coordinates;

import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.StockViewHolder> {

    List<String> infolist;
    List<String> periods;
    List<String> scoredteams;
    List<String> currentGoals;
    List<Coordinates> gooalCoordinates;

    public CardViewAdapter(List<String> infolist, List<String> periods, List<String> scoredteams, List<Coordinates> gooalCoordinates, List<String> currentGoals) {
        this.infolist = infolist;
        this.periods = periods;
        this.scoredteams = scoredteams;
        this.gooalCoordinates = gooalCoordinates;
        this.currentGoals = currentGoals;
    }


    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        StockViewHolder pvh = new StockViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
        animation1.setDuration(1000);
        holder.cv.setAlpha(1f);
        holder.cv.startAnimation(animation1);
        try {
            holder.scoredlogo.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(scoredteams.get(position)))));
        } catch (Exception e) {
            e.printStackTrace();
            holder.scoredlogo.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), R.drawable.main_logo)));
        }
        holder.stockName.setText(" ("+currentGoals.get(position)+")" + " Period " + periods.get(position) + "\n " + infolist.get(position));

/*        ImageView goal = new ImageView(holder.itemView.getContext());
        goal.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(scoredteams.get(position)))));

        goal.getLayoutParams().width= RelativeLayout.LayoutParams.WRAP_CONTENT;
        goal.getLayoutParams().height=RelativeLayout.LayoutParams.WRAP_CONTENT;
        holder.frameLay.addView(goal);*/

    }

    @Override
    public int getItemCount() {
        return infolist.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView stockName;
        TextView stockPrice;
        ImageView scoredlogo;
        ImageView field;

        StockViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            stockName = (TextView) itemView.findViewById(R.id.person_name);
            scoredlogo = (ImageView) itemView.findViewById(R.id.scoredlogo);
            field = (ImageView) itemView.findViewById(R.id.icefield);


        }
    }


}