package com.mobile.luthkemp.luthkemp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;

/**
 * Created by J.Madzivadondo on 2017/05/16.
 */

public class PushReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context context, Intent intent) {
    // Default notification title/text
    String notificationTitle = "Pushy";
    String notificationText = "Test notification";

    // Attempt to extract the "message" property from the payload: {"message":"Hello World!"}
    if (intent.getStringExtra("message") != null) {
      notificationText = intent.getStringExtra("message");
      notificationTitle = intent.getStringExtra("title");
    }

    // Prepare a notification with vibration, sound and lights
    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
      .setSmallIcon(R.mipmap.ic_launcher)
      .setContentTitle(notificationTitle)
      .setContentText(notificationText)
      .setLights(Color.RED, 1000, 1000)
      .setVibrate(new long[]{0, 400, 250, 400})
      .setColor(context.getResources().getColor(R.color.colorPrimary))
      .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
      .setContentIntent(PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT));

    // Get an instance of the NotificationManager service
    NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

    // Build the notification and display it
    mNotifyMgr.notify(1, mBuilder.build());
  }
}