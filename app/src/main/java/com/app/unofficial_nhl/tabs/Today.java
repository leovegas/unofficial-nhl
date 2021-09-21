package com.app.unofficial_nhl.tabs;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.*;
import android.view.*;
import android.widget.*;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.app.unofficial_nhl.NetworkService;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.helper_classes.ListRow;
import com.app.unofficial_nhl.helper_classes.MyCustomArrayAdapter;
import com.app.unofficial_nhl.helper_classes.StaticData;
import com.app.unofficial_nhl.pojos.Game;
import com.app.unofficial_nhl.pojos.Teams;
import com.google.android.material.navigation.NavigationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Today extends Fragment {

    private ArrayList<Game> gamesByDate = new ArrayList<>();
    String teamHome = "";
    String teamAway = "";
    String detailedState = "";
    String venueName = "";
    String gameTime = "";
    String gameDate = "";
    String homeScore = "";
    String awayScore = "";



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View root = inflater.inflate(R.layout.fragment_yesteday_today_tomorrow, container, false);
        SimpleDateFormat sdfDateToday = new SimpleDateFormat("yyyy-MM-dd");
        ProgressBar loadingBar = root.findViewById(R.id.progressBar);
        loadingBar.setVisibility(View.VISIBLE);

        NetworkService.getInstance()
                .getJSONApi()
            //    .getSheduledGamesByDate(sdfDateToday.format(new Date(System.currentTimeMillis())))
                .getSheduledGamesByDate("2021-05-16")
                .enqueue(new Callback<Teams>() {
                    @Override
                    public void onResponse(@NonNull Call<Teams> call, @NonNull Response<Teams> response) {
                        System.out.println(response);
                        Teams data = response.body();
                        if (!data.getDates().isEmpty()) {
                            gamesByDate.addAll(data.getDates().get(0).getGames());
                        }

                        ArrayList<ListRow> alldata = new ArrayList<ListRow>();

                        System.out.println(gamesByDate.size());

                        for (Game game : gamesByDate) {
                            teamHome = game.getTeams().getHome().getTeam().getName();
                            teamAway = game.getTeams().getAway().getTeam().getName();
                            detailedState = game.getStatus().getDetailedState();
                            venueName = game.getVenue().getName();
                            gameTime = getDateOrTime(game.getGameDate(), 2);
                            gameDate = getDateOrTime(game.getGameDate(), 1);
                            homeScore = String.valueOf(game.getTeams().getHome().getScore());
                            awayScore = String.valueOf(game.getTeams().getAway().getScore());

                            @DrawableRes
                            Drawable logo_team1 = resizeImage(StaticData.logosMap.get(game.getTeams().getHome().getTeam().getName()));
                            @DrawableRes
                            Drawable logo_team2 = resizeImage(StaticData.logosMap.get(game.getTeams().getAway().getTeam().getName()));


                            ListRow listRow = new ListRow(teamHome, teamAway, venueName, gameTime, gameDate, detailedState, awayScore, homeScore, logo_team1, logo_team2);
                            alldata.add(listRow);

                            loadingBar.setVisibility(View.GONE);

                        }

                        MyCustomArrayAdapter adapter = new MyCustomArrayAdapter(getActivity(), alldata);
                        final ListView listview = (ListView) root.findViewById(R.id.listview);
                        listview.setAdapter(adapter);

                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Toast.makeText(getActivity(), alldata.get(position).awayScore+"", Toast.LENGTH_SHORT).show();
                                cardflip(view,root.getContext());
                            }
                        });
                        listview.setOnDragListener(new View.OnDragListener() {

                            @Override
                            public boolean onDrag(View v, DragEvent event) {
                                NavigationView navigationView = root.findViewById(R.id.nav_view);
                                navigationView.animate()
                                        .alpha(100)
                                        .setDuration(2000)
                                        .start();

                                return false;
                            }
                        });

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

    public synchronized void cardflip (View v,Context context) {

        System.out.println(v.getTag());
        v.animate().withLayer()
                    .rotationY(90)
                    .setDuration(400)
                    .withEndAction(
                            new Runnable() {
                                @Override public void run() {
                                    float scale = context.getResources().getDisplayMetrics().density;
                                    float distance = v.getCameraDistance() * (scale + (scale / 3));
                                    v.setCameraDistance(distance * scale);
                                    v.setRotationY(-90);
                                    v.findViewById(R.id.away_score).setVisibility(View.VISIBLE);
                                    v.findViewById(R.id.home_score).setVisibility(View.VISIBLE);

                                    v.animate().withLayer()
                                            .rotationY(0)
                                            .setDuration(400)
                                            .start();
                                    v.findViewById(R.id.away_score).animate().withLayer()
                                            .rotationY(0)
                                            .setDuration(400)
                                            .start();
                                    v.findViewById(R.id.home_score).animate().withLayer()
                                            .rotationY(0)
                                            .setDuration(400)
                                            .start();


                                }
                            }

                    ).start();

    }

    private void doSomethingOnUi(Object response) {
        Handler uiThread = new Handler(Looper.getMainLooper());
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Please wait", 5000).show();
            }
        });
    }

    public void doSomeTaskAsync() {
        HandlerThread ht = new HandlerThread("MyHandlerThread");
        ht.start();
        Handler asyncHandler = new Handler(ht.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Object response = msg.obj;
                doSomethingOnUi(response);
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // your async code goes here.
                try {
                    Thread.sleep(1000);


                    Message message = new Message();
                    message.obj = "My Message!";

                    asyncHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncHandler.post(runnable);
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
    /************************ Resize Bitmap *********************************/


    public String getDateOrTime(String stringTime, int index) {
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
        if (index == 1) return sdfDate.format(date);
        if (index == 2) return sdfTime.format(date);
        return sdfDate.format(date);
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

