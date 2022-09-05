package com.app.unofficial_nhl.helper_classes;

import android.app.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.view.Display;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationManagerCompat;
import com.allyants.notifyme.NotifyMe;
import com.app.unofficial_nhl.MainActivity2;
import com.app.unofficial_nhl.R;
import com.app.unofficial_nhl.alarms.DailyReceiver;
import com.app.unofficial_nhl.pojos.Game;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StaticData {
    public static Map<String, Integer> logosMap = null;
    public static Map<String, Integer> teamToIdMap = null;

    static {
        Map<String, Integer> aMap = new HashMap<String, Integer>();
        aMap.put("New Jersey Devils", R.drawable.new_jersey_devils_logo);
        aMap.put("New York Islanders", R.drawable.new_york_islanders_logo);
        aMap.put("New York Rangers", R.drawable.new_york_rangers_logo);
        aMap.put("Philadelphia Flyers", R.drawable.philadelphia_flyers_logo);
        aMap.put("Pittsburgh Penguins", R.drawable.pittsburgh_penguins_logo);
        aMap.put("Boston Bruins", R.drawable.boston_bruins_logo);
        aMap.put("Buffalo Sabres", R.drawable.buffalo_sabres_logo);
        aMap.put("Winnipeg Jets", R.drawable.winnipeg_jets_logo);
        aMap.put("Washington Capitals", R.drawable.washington_capitals_logo);
        aMap.put("Vegas Golden Knights", R.drawable.vegas_golden_knights_logo);
        aMap.put("Vancouver Canucks", R.drawable.vancouver_canucks_logo);
        aMap.put("Toronto Maple Leafs", R.drawable.toronto_maple_leafs_logo);
        aMap.put("Tampa Bay Lightning", R.drawable.tampa_bay_lightning_logo);
        aMap.put("St. Louis Blues", R.drawable.st_louis_blues_logo);
        aMap.put("San Jose Sharks", R.drawable.san_jose_sharks_logo);
        aMap.put("Ottawa Senators", R.drawable.ottawa_senators_logo);
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
        aMap.put("Arizona Coyotes", R.drawable.arizona_coyotes_logo);
        aMap.put("Anaheim Ducks", R.drawable.anaheim_ducks_logo);
        aMap.put("Seattle Kraken", R.drawable.seattle_kraken_logo);
        logosMap = Collections.unmodifiableMap(aMap);
    }

    static {
        Map<String, Integer> TeamToId = new HashMap<String, Integer>();
        TeamToId.put("New Jersey Devils", 1);
        TeamToId.put("New York Islanders", 2);
        TeamToId.put("New York Rangers", 3);
        TeamToId.put("Philadelphia Flyers", 4);
        TeamToId.put("Pittsburgh Penguins", 5);
        TeamToId.put("Boston Bruins", 6);
        TeamToId.put("Buffalo Sabres", 7);
        TeamToId.put("Montréal Canadiens", 8);
        TeamToId.put("Ottawa Senators", 9);
        TeamToId.put("Toronto Maple Leafs", 10);
        TeamToId.put("Carolina Hurricanes", 12);
        TeamToId.put("Florida Panthers", 13);
        TeamToId.put("Tampa Bay Lightning", 14);
        TeamToId.put("Washington Capitals", 15);
        TeamToId.put("Chicago Blackhawks", 16);
        TeamToId.put("Detroit Red Wings", 17);
        TeamToId.put("Nashville Predators", 18);
        TeamToId.put("St. Louis Blues", 19);
        TeamToId.put("Calgary Flames", 20);
        TeamToId.put("Colorado Avalanche", 21);
        TeamToId.put("Edmonton Oilers", 22);
        TeamToId.put("Vancouver Canucks", 23);
        TeamToId.put("Anaheim Ducks", 24);
        TeamToId.put("Dallas Stars", 25);
        TeamToId.put("Los Angeles Kings", 26);
        TeamToId.put("Columbus Blue Jackets", 29);
        TeamToId.put("Minnesota Wild", 30);
        TeamToId.put("Winnipeg Jets", 52);
        TeamToId.put("Vegas Golden Knights", 54);
        TeamToId.put("San Jose Sharks", 28);
        TeamToId.put("Arizona Coyotes", 53);
        TeamToId.put("Seattle Kraken", 55);
        teamToIdMap = Collections.unmodifiableMap(TeamToId);
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public static String getDateOrTime(String stringTime, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("h:mm a");
        SimpleDateFormat sdfTextDate = new SimpleDateFormat("EEE MMM dd h:mma");


        Date date = new Date();
        try {
            date = sdf.parse(stringTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());

        if (index == 1) return sdfDate.format(date);
        if (index == 2) return sdfTime.format(date);
        if (index == 3) return sdfTextDate.format(date);

        return sdfDate.format(date);
    }

    public static Map<Calendar,Integer> StrToCalendar(String strDate) {
        Map<Calendar,Integer> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());

        if (date != null && date.getTime() > new Date().getTime()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            map.put(cal, new Random().nextInt(100000));
            return map;
        }
        return null;
    }

    public static void setAlarm(Context context, Calendar calendar, int randomID, String teamname, String date) {

        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra("randomID",(Integer) randomID);
        myIntent.putExtra("teamname",(String) teamname);
        myIntent.putExtra("date",(String) date);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, randomID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-(3600 * 1000), pendingIntent);
        System.out.println("alarm set " + randomID);

    }
    public static void removeAlarm(Context context, Class<?> cls, int id, String teamname) {
        // Disable a receiver
        TinyDB tinyDB = new TinyDB(context);
/*
        ComponentName receiver = new ComponentName(context, DailyReceiver.class);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
*/

        ArrayList<Integer> list = tinyDB.getListInt(teamname+"1");

        for (Integer integer : list) {
            Intent intent1 = new Intent(context, DailyReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    integer, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            am.cancel(pendingIntent);
            pendingIntent.cancel();
            System.out.println("alarm unset "+id + " al id "+ integer);
        }
        list = new ArrayList<>();
        tinyDB.putListInt(teamname+"1",list);




    }
    public static Drawable resizeImage(int imageResource, Activity activity, Display display) {// R.drawable.large_image
        // Get device dimensions

        double deviceWidth = display.getWidth();

        if(imageResource==0) imageResource = R.drawable.main_logo;

        BitmapDrawable bd = (BitmapDrawable) activity.getResources().getDrawable(
                imageResource);
        double imageHeight = bd.getBitmap().getHeight();
        double imageWidth = bd.getBitmap().getWidth();

        double ratio = deviceWidth / imageWidth;
        int newImageHeight = (int) (imageHeight * ratio);

        Bitmap bMap = BitmapFactory.decodeResource(activity.getResources(), imageResource);
        Drawable drawable = new BitmapDrawable(activity.getResources(),
                getResizedBitmap(bMap, newImageHeight, (int) deviceWidth));

        return drawable;
    }


    public static Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {

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

    public static void showAbout(Context context, View view) {

        // setup the alert builder
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(context);
        builder.setTitle("About");
        builder.setMessage("!Important note!  \nThis app is not an official National Hockey League app. All trademarks used in the app are used for the sole purpose of identifying  the respective teams and franchises and remain the property of their respective owners. \n" +
                "NHL and the NHL Shield are registered trademarks of the National Hockey League. NHL and NHL team marks are the property of the NHL and its teams. \n © NHL 2021. All Rights Reserved. \n \n"
                +"App developer  \ntimplay89@gmail.com");

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
