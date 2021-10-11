package com.app.unofficial_nhl.helper_classes;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.service.notification.StatusBarNotification;
import android.widget.Toast;
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

    public static String remind(Context context, String text, Calendar date) {
        NotifyMe.Builder notifyMe = new NotifyMe.Builder(context);
        notifyMe.title(text);
        notifyMe.content("String content");
        notifyMe.color(0, 0, 0, 100);//Color of notification header
        notifyMe.led_color(0, 0, 0, 100);//Color of LED when
        notifyMe.time(date);//The time to popup notification
        notifyMe.delay(0);//Delay in ms
        notifyMe.large_icon(R.drawable.main_logo);//Icon resource by ID
        notifyMe.rrule("FREQ=MINUTELY;INTERVAL=5;COUNT=1");//RRULE for frequency of notification
        notifyMe.addAction(new Intent(context, MainActivity2.class), "String text"); //The action will call the intent when pressed
        notifyMe.build();


        if (NotificationManagerCompat.from(context).getNotificationChannels().size() > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationManagerCompat.from(context).getNotificationChannels().forEach(notificationChannel -> {
                    System.out.println(notificationChannel.getId());
                });
                return NotificationManagerCompat.from(context).getNotificationChannels().get(NotificationManagerCompat.from(context).getNotificationChannels().size() - 1).getId();
            }
        }
        System.out.println("NULL");
        return null;
    }

    public static void removeNoti(Context context) {
        NotificationManagerCompat.from(context).cancelAll();
        System.out.println("Removed");
        AlarmManager alarmManager =
                (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,new Intent(),PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
    }

    public static Map<Calendar,Integer> StrToCalendar(String strDate) {
        Map<Calendar,Integer> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null && date.getTime() > new Date().getTime()) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            map.put(cal, new Random().nextInt(100000));
            return map;
        }
        return null;
    }

    public static void setAlarm(Context context, Calendar calendar, int randomID, String teamname, String date) {

        System.out.println(calendar.getTime().toString());
        Intent myIntent = new Intent(context, DailyReceiver.class);
        myIntent.putExtra("randomID",(Integer) randomID);
        myIntent.putExtra("teamname",(String) teamname);
        myIntent.putExtra("date",(String) date);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, randomID, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
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

}
