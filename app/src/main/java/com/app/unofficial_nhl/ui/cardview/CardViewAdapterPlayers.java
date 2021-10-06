package com.app.unofficial_nhl.ui.cardview;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.helper_classes.TinyDB;

import java.util.List;

public class CardViewAdapterPlayers extends RecyclerView.Adapter<CardViewAdapterPlayers.StockViewHolder> {

    public CardViewAdapterPlayers(List<String> playerNames, List<String> playerJerseyNumbers, List<String> playerPositionTypes, List<String> playerPositionNames,   List<Integer> ids) {
        this.playerNames = playerNames;
        this.playerPositionNames = playerPositionNames;
        this.playerPositionTypes = playerPositionTypes;
        this.playerJerseyNumbers = playerJerseyNumbers;
        this.ids = ids;
    }

    List<String> playerNames;
    List<String> playerPositionNames;
    List<String> playerPositionTypes;
    List<String> playerJerseyNumbers;
    List<Integer> ids;


    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_player, parent, false);
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
        holder.player_name.setText(playerNames.get(position));
        holder.position_text.setText(playerPositionNames.get(position));
        holder.jersey_number.setText(playerJerseyNumbers.get(position));

        System.out.println(playerPositionTypes.get(position));
        switch (playerPositionTypes.get(position)) {
            case "Forward": holder.position_logo.setImageResource(R.drawable.forward); return;
            case "Defenseman": holder.position_logo.setImageResource(R.drawable.defenceman); return;
            case "Goalie": holder.position_logo.setImageResource(R.drawable.goalie); return;
            default: holder.position_logo.setImageResource(R.drawable.forward); return;

        }

        //holder.logo.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(shortnames.get(position)))));
        //holder.shortname.setText(shortnames.get(position));


/*        ImageView goal = new ImageView(holder.itemView.getContext());
        goal.setImageDrawable((ContextCompat.getDrawable(holder.cv.getContext(), StaticData.logosMap.get(scoredteams.get(position)))));

        goal.getLayoutParams().width= RelativeLayout.LayoutParams.WRAP_CONTENT;
        goal.getLayoutParams().height=RelativeLayout.LayoutParams.WRAP_CONTENT;
        holder.frameLay.addView(goal);*/

    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView player_name;
        TextView position_text;
        ImageView position_logo;
        TextView jersey_number;

        StockViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            player_name = (TextView) itemView.findViewById(R.id.player_name);
            position_text = (TextView) itemView.findViewById(R.id.positionText);
            jersey_number = (TextView) itemView.findViewById(R.id.jerseyNumber);
            position_logo = (ImageView) itemView.findViewById(R.id.positionLogo);

        }
    }


}