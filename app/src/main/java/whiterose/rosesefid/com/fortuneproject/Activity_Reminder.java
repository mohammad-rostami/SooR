package whiterose.rosesefid.com.fortuneproject;

import android.app.AlarmManager;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Mohammad on 5/13/2017.
 */

public class Activity_Reminder extends AppCompatActivity implements FragmentSelect.OnFragmentInteractionListener {

    public static int Minutes;
    public static int Hour;
    public static ImageView add;
    private static TextView mTime;
    private static FrameLayout frame;
    public static ImageView img;
    private static Intent i;
    private ImageView del;
    public static LinearLayout ll;
    public static Integer[] mArray_Images = new Integer[]{R.drawable.first, R.drawable.second, R.drawable.third,
            R.drawable.forth, R.drawable.fifth, R.drawable.sixth, R.drawable.seventh, R.drawable.eighth, R.drawable.ninth,
            R.drawable.tenth, R.drawable.eleventh, R.drawable.twelfth};
    private static AlarmManager alarmManager;
    private ImageView btnBack;
    private ImageView btn_shares;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);


        btnBack = (ImageView) findViewById(R.id.btnBack);
        btn_shares = (ImageView) findViewById(R.id.btn_share);
        btn_shares.setVisibility(View.GONE);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity_Reminder.super.onBackPressed();
            }
        });

        ll = (LinearLayout) findViewById(R.id.layout);
        add = (ImageView) findViewById(R.id.add);
        img = (ImageView) findViewById(R.id.img);
        del = (ImageView) findViewById(R.id.del);
        add = (ImageView) findViewById(R.id.add);
        mTime = (TextView) findViewById(R.id.time);
        frame = (FrameLayout) findViewById(R.id.container);
        try {
            G.new_string = G.alarm_pref.getString("ALARM", null);
            String result = G.new_string;
            String hour = result.substring(0, result.indexOf(":"));
            String min = result.substring(result.indexOf(":"), result.indexOf("!"));
            String pos = result.substring(result.indexOf("!"), result.length());
            mTime.setText(hour + " : " + min);
//            img.setImageResource(Activity_Reminder.mArray_Images[Integer.parseInt(pos)]);
            ll.setVisibility(View.VISIBLE);
            Log.i("mohammad", result);
            if (!G.new_string.equals("")){
                add.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Activity_Reminder.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
//                        eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        Hour = selectedHour;
                        Minutes = selectedMinute;
                        FragmentSelect myf = new FragmentSelect();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.add(R.id.container, myf);
                        transaction.commit();
                        frame.setVisibility(View.VISIBLE);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                alarmManager = (AlarmManager) G.context.getSystemService(Context.ALARM_SERVICE);
                i = new Intent(G.context, OnAlarmReceiver.class);
                PendingIntent pi = PendingIntent.getBroadcast(G.context, 0, i, PendingIntent.FLAG_ONE_SHOT);
                alarmManager.cancel(pi);

                G.new_string = "";
                Log.i("size after delete", G.new_string);
                SharedPreferences.Editor editor = G.alarm_pref.edit();
                editor.putString("ALARM", G.new_string);
                editor.commit();
            }
        });

//        Intent i1 = new Intent(this, OnAlarmReceiver.class);
//        PendingIntent pi1 = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_ONE_SHOT);
//        Calendar calendar1 = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND) + 20);
//        AlarmManager alarmManager1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        alarmManager1.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi1);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void alarmSetter() {
        add.setVisibility(View.GONE);
        mTime.setVisibility(View.VISIBLE);
        mTime.setText(Hour + " : " + Minutes);

        i = new Intent(G.context, OnAlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(G.context, 0, i, PendingIntent.FLAG_ONE_SHOT);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Hour);
        calendar.set(Calendar.MINUTE, Minutes);
        calendar.set(Calendar.SECOND, 0);
        alarmManager = (AlarmManager) G.context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

        SharedPreferences.Editor editor = G.alarm_pref.edit();

        G.new_string = Hour + ":" + Minutes + "!" + Activity_Main.selectedPosition;
        editor.putString("ALARM", G.new_string);
        editor.commit();
        frame.setVisibility(View.GONE);

        Log.i("size after add", G.new_string);

    }
}
