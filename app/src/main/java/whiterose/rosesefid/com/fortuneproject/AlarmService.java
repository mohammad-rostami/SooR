package whiterose.rosesefid.com.fortuneproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class AlarmService extends WakeIntentService {
    private final IBinder binder = new LocalBinder();
    // Registered callbacks
    private ServiceCallbacks serviceCallbacks;

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    void doReminderWork(Intent intent) {
        NotificationManager manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(this, Activity_Reminder.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) G.context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap bitmap = BitmapFactory.decodeResource(G.context.getResources(), R.drawable.dragon);

        Notification notif = new Notification.Builder(G.context)
//                .setContentIntent(pendingIntent)
                .setContentTitle("زنگ هشدار")
                .setContentText("یک آیتم رو توی یادآور ست کردی")
                .setSmallIcon(R.drawable.dragon_outline)
                .setLargeIcon(bitmap)
//                .setStyle(new Notification.BigPictureStyle().bigPicture(R.drawable.dragon_outline))
                .build();
        notif.defaults |= Notification.DEFAULT_ALL;
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        int id = 123456789;
        notificationManager.notify(id, notif);

//        Notification note = new Notification(R.drawable.dragon_outline, "Alarm", System.currentTimeMillis());
////        note.setLatestEventInfo(this, "Title", "Text", pi);
//        note.defaults |= Notification.DEFAULT_ALL;
//        note.flags |= Notification.FLAG_AUTO_CANCEL;
//        int id = 123456789;
//        manager.notify(id, note);
        try {
            G.new_string = "";
            Log.i("mohammad", G.new_string);

        } catch (Exception e) {
            Log.i("mohammad", "error");

        }
    }


    // Class used for the client Binder.
    public class LocalBinder extends Binder {
        AlarmService getService() {
            // Return this instance of MyService so clients can call public methods
            return AlarmService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void setCallbacks(ServiceCallbacks callbacks) {
        serviceCallbacks = callbacks;
    }
}