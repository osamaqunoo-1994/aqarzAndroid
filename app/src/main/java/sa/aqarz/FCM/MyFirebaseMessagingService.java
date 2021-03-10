package sa.aqarz.FCM;

/**
 * Created by (Osama Qunoo) on 1/4/2018.
 * Mobile 0599954821
 * Email  osama0qunoo@gmail.com
 */
//I/System.out: getData1{id=0, body=common.your order has accepted, type=rate, title=done}//hawawi

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.hawk.Hawk;

import sa.aqarz.Activity.SplashScreenActivity;
import sa.aqarz.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


        Hawk.put("not", "1");


        if (remoteMessage.getNotification() != null) {
            RemoteMessage.Notification notification = remoteMessage.getNotification();
//            sendNotification("xxx",notification.getTitle(),notification.getBody(),"");

            showNotification(notification.getTitle(), notification.getBody(), "xxx");


            System.out.println("((((((" + notification.getTitle() + "((((((" + notification.getBody() + "((((((" + "xxx");


//            System.out.println("notification" + notification.getBody() + "%%%% " + notification.getTitle());
        } else {
//            sendNotification(remoteMessage.getData().get("type"),"NaqlThaqeel",remoteMessage.getData().get("body"),"");
            showNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"), remoteMessage.getData().get("type"));

            System.out.println("######((((((" + remoteMessage.getData().get("title") + "((((((" + remoteMessage.getData().get("body") + "((((((" + remoteMessage.getData().get("type"));


            //
//
//            System.out.println("getData1" + remoteMessage.getData());
//            System.out.println(remoteMessage.getData().toString() + "%%%% " + remoteMessage.getData().toString());

        }


    }

    /**
     * Create and show a custom notification containing the received FCM message.
     * <p>
     * //     * @param notification FCM notification payload received.
     * //     * @param data FCM data payload received.
     */

    private void showNotification(String title, String body, String type) {


        try {
//            MainActivity.view_notfication();


            Intent broadCastIntent = new Intent();
            broadCastIntent.setAction("com.ojastec.broadcastreceiverdemo");

            // uncomment this line if you want to send data
//            broadCastIntent.putExtra("data", "abc");

            sendBroadcast(broadCastIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent intent;
        intent = new Intent(this, SplashScreenActivity.class);
//        System.out.println("typetype" + type);


        System.out.println("type_notfication" + type);

        if (type != null) {
            if (type.equals("message")) {
                intent.putExtra("typex", "message");

            } else if (type.equals("new_order")) {
                intent.putExtra("typex", "new_order");

            } else {
                intent.putExtra("typex", "xxx");

            }
        }


        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        int color = getResources().getColor(R.color.colorPrimary);
        long[] v = {500, 1000};
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);


        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);
//        Log.d("Hay8","DCM8");
//        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
//        Log.d("Hay9","DCM9");
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        Log.d("Hay10","DCM10");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setSound(defaultSoundUri)

                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);
        Log.d("Hay11", "DCM11");


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d("Hay12", "DCM12");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId("com.myApp");
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "com.myApp",
                    "My App",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        notificationManager.notify(2, builder.build());
    }
}

