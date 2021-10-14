package com.app.unofficial_nhl.alarms;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.allyants.notifyme.ActionReceiver;
import com.allyants.notifyme.DeletePendingIntent;
import com.app.unofficial_nhl.MainActivity2;
import com.app.unofficial_nhl.R;

import static com.allyants.notifyme.Notification.NotificationEntry.NOTIFICATION_SMALL_ICON;

public class DailyReceiver extends BroadcastReceiver {
    private static int NOTIFICATION_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        String quote;

        Intent notificationIntent = new Intent(context, MainActivity2.class);
        Integer randomID = intent.getIntExtra("randomID",0);
        String teamname = intent.getStringExtra("teamname");
        String date = intent.getStringExtra("date");

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        // get your quote here
        quote = "doSomeMethod()";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, String.valueOf(randomID));

        mBuilder.setSmallIcon(R.drawable.ic_check_circle);
       // mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher2));
        mBuilder.setContentTitle(teamname + " "+date);
        mBuilder.setContentText("Check results in our app");
        mBuilder.setColor(Color.BLUE);
        mBuilder.setVibrate(new long[]{1000, 1000, 1000});

        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(uri);
        Intent deleteIntent = new Intent(context, MainActivity2.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, randomID, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setDeleteIntent(pendingIntent);
        Notification notification = mBuilder.build();

        notification.flags = Notification.FLAG_SHOW_LIGHTS;
        notification.ledOnMS = 300;
        notification.ledOffMS = 1000;
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nc = new NotificationChannel(randomID+"", "games", NotificationManager.IMPORTANCE_HIGH);
            nc.enableLights(true);
            mNotificationManager.createNotificationChannel(nc);
        }
        mNotificationManager.notify(NOTIFICATION_ID, notification);
        NOTIFICATION_ID++;
    }


}