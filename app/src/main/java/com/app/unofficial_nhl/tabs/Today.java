package com.app.unofficial_nhl.tabs;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.MyCustomArrayAdapter;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Teams;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Today extends Fragment {

    public static Map<String, Integer> logosMap = null;


    static {
        Map<String,Integer> aMap = new HashMap<String, Integer>();
        aMap.put("Winnipeg Jets", R.drawable.winnipeg_jets_logo);
        aMap.put("Washington Capitals", R.drawable.washington_capitals_logo);
        aMap.put("Vegas Golden Knights", R.drawable.vegas_golden_knights_logo);
        aMap.put("Vancouver Canucks", R.drawable.vancouver_canucks_logo);
        aMap.put("Toronto Maple Leafs", R.drawable.toronto_maple_leafs_logo);
        aMap.put("Tampa Bay Lightning", R.drawable.tampa_bay_lightning_logo);
        aMap.put("St. Louis Blues", R.drawable.st_louis_blues_logo);
        aMap.put("San Jose Sharks", R.drawable.san_jose_sharks_logo);
        aMap.put("Pittsburgh Penguins", R.drawable.pittsburgh_penguins_logo);
        aMap.put("Philadelphia Flyers", R.drawable.philadelphia_flyers_logo);
        aMap.put("Ottawa Senators", R.drawable.ottawa_senators_logo);
        aMap.put("New York Rangers", R.drawable.new_york_rangers_logo);
        aMap.put("New York Islanders", R.drawable.new_york_islanders_logo);
        aMap.put("New Jersey Devils", R.drawable.new_jersey_devils_logo);
        aMap.put("Nashville Predators", R.drawable.nashville_predators_logo);
        aMap.put("Montréal Canadiens", R.drawable.montreal_canadiens_logo);
        aMap.put("Minnesota Wild", R.drawable.minnesota_wild_logo);
        aMap.put("Los Angeles Kings", R.drawable.los_angeles_kings_logo);
        aMap.put("Florida Panthers", R.drawable.florida_panthers_logo);
        aMap.put("Edmonton Oilers", R.drawable.edmonton_oilers_logo);
        aMap.put("Detroit Red Wings", R.drawable.detroit_red_wings_logo);
        aMap.put("Dallas Stars", R.drawable.dallas_stars_logo);
        aMap.put("Columbus Blue Jackets", R.drawable.columbus_blue_jackets_logo);
        aMap.put("Colorado Avalanche", R.drawable.colorado_avalanche_logo);
        aMap.put("Chicago Blackhawks", R.drawable.chicago_blackhawks_logo);
        aMap.put("Carolina Hurricanes", R.drawable.carolina_hurricanes_logo);
        aMap.put("Calgary Flames", R.drawable.calgary_flames_logo);
        aMap.put("Buffalo Sabres", R.drawable.buffalo_sabres_logo);
        aMap.put("Boston Bruins", R.drawable.boston_bruins_logo);
        aMap.put("Arizona Coyotes", R.drawable.arizona_coyotes_logo);
        aMap.put("Anaheim Ducks", R.drawable.anaheim_ducks_logo);
        logosMap = Collections.unmodifiableMap(aMap);
    }

    private ArrayList<Game> gamesByDate = new ArrayList<>();
    String teamHome = "";
    String teamAway = "";
    String detailedState  = "";
    String venueName  = "";
    String gameTime  = "";
    String gameDate  = "";

        @Override
        public View onCreateView(
                LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState
        ) {
            View root = inflater.inflate(R.layout.fragment_today, container, false);

            NetworkService.getInstance()
                    .getJSONApi()
                    .getSheduledGamesByDate("2021-01-18")
                    .enqueue(new Callback<Teams>() {
                        @Override
                        public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                            Teams data = response.body();
                            gamesByDate.addAll(data.getDates().get(0).getGames());

                            ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                            System.out.println(gamesByDate.size());
                        for (Game game : gamesByDate)
                        {
                            teamHome = game.getTeams().getHome().getTeam().getName();
                            teamAway = game.getTeams().getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(),false);
                            gameDate = getDateOrTime(game.getGameDate(),true);

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(logosMap.get(game.getTeams().getAway().getTeam().getName()));


                            ListRow listRow = new ListRow(teamHome, teamAway, logo_team1, logo_team2, venueName, gameTime, gameDate, detailedState);
                            alldata.add(listRow);
                        }

                            MyCustomArrayAdapter adapter = new MyCustomArrayAdapter (getActivity(), alldata);
                            final ListView listview = (ListView) root.findViewById(R.id.listview);
                            listview.setAdapter(adapter);

                        }

                        @Override
                        public void onFailure(@NonNull Call<Teams> call, @NonNull Throwable t) {
                            System.out.println("Error occurred while getting request!");
                            t.printStackTrace();
                        }

                    });
            // Inflate the layout for this fragment
            return root;
        }
    /************************ Resize Bitmap *********************************/
    public Drawable resizeImage(int imageResource) {// R.drawable.large_image
        // Get device dimensions
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        double deviceWidth = display.getWidth();

        BitmapDrawable bd = (BitmapDrawable) this.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(this.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

        int width = bm.getWidth();
        int height = bm.getHeight();

        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height,
                matrix, false);

        return resizedBitmap;
    }

    public String getDateOrTime(String stringTime, boolean areYouNeedDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getDefault());
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");

        Date date = new Date();
        try {
            date = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (areYouNeedDate) return sdfDate.format(date);
        else return sdfTime.format(date);
    }


    private static class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }



    }
    }

