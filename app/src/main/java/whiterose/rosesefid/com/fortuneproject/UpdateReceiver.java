package whiterose.rosesefid.com.fortuneproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateReceiver extends BroadcastReceiver {

    private Handler handler;
    private Runnable r;
    private ArrayList array;
    private SharedPreferences idPref;
    private SharedPreferences boolPref;
    private SharedPreferences.Editor idPrefEdit;
    private SharedPreferences.Editor boolPrefEdit;
    private Set<String> set;
    private Bitmap icone;
    private String title;
    private String text;
    private String icon;
    private String target;
    private PendingIntent pendingIntent;


    @Override
    public void onReceive(final Context context, Intent intent) {
        array = new ArrayList();
        idPref = PreferenceManager.getDefaultSharedPreferences(context);
        boolPref = PreferenceManager.getDefaultSharedPreferences(context);
        idPrefEdit = idPref.edit();
        boolPrefEdit = boolPref.edit();


        boolean rb0 = boolPref.getBoolean("rb0", false);
        if (!rb0) {
            set = new HashSet<String>();
            set.addAll(array);
            idPrefEdit.putStringSet("ARRAY", set);
            idPrefEdit.commit();

            boolPrefEdit.putBoolean("rb0", true);
            boolPrefEdit.commit();

        }

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        final NetworkInfo activeNetInfo1 = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        final boolean isConnected = (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) || (activeNetInfo1 != null && activeNetInfo1.isConnectedOrConnecting());
        //Declare the timer
        final Timer t = new Timer();
        //Set the schedule function and rate
        t.scheduleAtFixedRate(new TimerTask() {
                                  @Override
                                  public void run() {

                                      if (isConnected) {
                                          new Async().execute("http://www.soooor.ir/update_texts/notification.txt");
                                      } else {
                                          Log.i("NET", "is connected = " + isConnected);
                                      }
                                  }
                              },
                0,
                3600000);
    }


    private class Async extends Webservice.GetData {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                String mStr_id = result.substring(0, result.indexOf(":"));
                int id = Integer.parseInt(mStr_id);

                set = idPref.getStringSet("ARRAY", null);
                List<String> sample = new ArrayList<String>(set);

                if (sample.contains(id)) {
                    Log.i("check result", "contained");
                } else {
                    String text = result.substring(result.indexOf(":") + 1, result.length());
                    String title = "Fortune";

                    array.clear();
                    for (int i = 0; i < sample.size(); i++) {
                        array.add(sample.get(i));
                    }

                    array.add(id);
                    set = new HashSet<String>();
                    set.addAll(array);
                    idPrefEdit.putStringSet("ARRAY", set);
                    idPrefEdit.commit();
                    notificationBuilder(title,text);
                }

            } catch (NullPointerException e) {
                Log.e("ERROR", "no id");
            } catch (StringIndexOutOfBoundsException e) {
                Log.e("ERROR", "no id");
            }
        }
    }

    class RetrieveImage extends AsyncTask<String, Void, Bitmap> {

        private Exception exception;

        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                icone = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return icone;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(Bitmap icone) {
            route(target);
//            Intent intent = new Intent(G.context, Activity_Main.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                    PendingIntent.FLAG_ONE_SHOT);
            NotificationManager notificationManager = (NotificationManager) G.context.getSystemService(Context.NOTIFICATION_SERVICE);

            Bitmap bitmap = BitmapFactory.decodeResource(G.context.getResources(), R.drawable.dragon);

            Notification notif = new Notification.Builder(G.context)
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(R.drawable.dragon)
                    .setLargeIcon(bitmap)
                    .setStyle(new Notification.BigPictureStyle().bigPicture(icone))
                    .build();
            notif.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(1, notif);
        }
    }

    private void route(String target) {
//        G.isUpdate = true;
//        G.target = "";
//        G.target_code = "";
//        G.target = target.substring(0, target.indexOf("::"));
//        G.target_code = target.substring(target.indexOf("::") + 2, target.length());
//        switch (G.target) {
//            case "category_info":
//                Activity_Main.target_cat = "category_info";
//                Intent intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "category_list":
//                Activity_Main.target_cat = "category_list";
//                intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "manufacturer_info":
//                Activity_Main.target_cat = "manufacturer_info";
//                intent = new Intent(G.context, Activity_Manufacturer.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "manufacturer_list":
//                Activity_Main.target_cat = "manufacturer_list";
//                intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "product_list":
//                Activity_Main.target_cat = "product_list";
//                intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "product_info":
//                Activity_Main.target_cat = "product_info";
//                intent = new Intent(G.context, Activity_Products.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "custom":
//                Activity_Main.target_cat = "custom";
//                intent = new Intent(G.context, Activity_Information.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "information_info":
//                Activity_Main.target_cat = "information_info";
//                intent = new Intent(G.context, Activity_Information.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "look_info":
//                Activity_Main.target_cat = "look_info";
//                intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//            case "look_list":
//                Activity_Main.target_cat = "look_list";
//                intent = new Intent(G.context, Activity_ProductsList.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                pendingIntent = PendingIntent.getActivity(G.context, 0 /* Request code */, intent,
//                        PendingIntent.FLAG_ONE_SHOT);
//                break;
//        }
    }

    private void notificationBuilder(String title,String text){
        NotificationManager notificationManager = (NotificationManager) G.context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap bitmap = BitmapFactory.decodeResource(G.context.getResources(), R.drawable.dragon);

        Notification notif = new Notification.Builder(G.context)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.dragon_outline)
                .setLargeIcon(bitmap)
//                            .setStyle(new Notification.s)
                .build();
        notif.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(1, notif);
    }
}