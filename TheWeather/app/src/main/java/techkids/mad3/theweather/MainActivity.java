package techkids.mad3.theweather;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
//    public static String NOTIFICATION_ID = "notification-id";
//    public static String NOTIFICATION = "notification";

/*/*SELECT * FROM tblWeather where Create_at*/
//select * from tblWeather where Datetime('now') > Create_at;
//select * from tblWeather where strftime('%H:%M:%S', Create_at ) > strftime('%H:%M:%S', '2016-05-31 03:56:50');

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, WeatherService.class);
        startService(intent);
        WeatherDBHelper sqliteOpenDbHelper = new WeatherDBHelper(this);
        SQLiteDatabase db = sqliteOpenDbHelper.getWritableDatabase();

        String[] projections = {WeatherDBHelper._ID,  WeatherDBHelper.CREATE_AT, WeatherDBHelper.DESCRIPTION};

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        simpleDateFormat.format(cal.getTime());
//        Log.d("Current Time ", String.valueOf(simpleDateFormat.format(cal.getTime())));

        try {
            java.util.Date date1 = simpleDateFormat.parse("2016-01-12 03:56:50");
            java.util.Date date2 = simpleDateFormat.parse("2016-11-12 04:26:00");
            java.util.Date currentDate = new java.util.Date();

            if (date1.compareTo(currentDate)<0)
                Log.d("Message", "date1 before current date");

            if (date2.compareTo(currentDate)>0)
                Log.d("Message", "date2 after current date");


            Cursor cursor = db.query(WeatherDBHelper.WEATHER_TABLE_NAME,
                    projections,
                    null,
                    null,
                    null,
                    null,
                    null);
            while(cursor.moveToNext()) {
                long id = cursor.getLong(WeatherDBHelper.ID_INDEX);
                String description = cursor.getString(WeatherDBHelper.DESCRIPTION_INDEX);
                String create_at = cursor.getString(WeatherDBHelper.CREATE_AT_INDEX);
                //Double temp = cursor.getDouble(0);
                java.util.Date getCreateAt = simpleDateFormat.parse(create_at);

                if (getCreateAt.compareTo(currentDate)<0)
                                    Log.d("result sql:",
                                            "ID: " + id
                                                    + " Create_at: " + create_at
                                                    + " Description: " + description);
                                    // + " Temperature: " + temp);
                else
                    Log.d("result sql", "No record");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume()
    {
        super.onResume();

//        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//                Notification notification = intent.getParcelableExtra(NOTIFICATION);
//                int id = intent.getIntExtra(NOTIFICATION_ID, 0);
//                notificationManager.notify(id, notification);
//                Log.d("Tao dayyyyyy", "okkkkkkkkkkkkkkkkkkkkk");
//            }
//        };
//
//        registerReceiver(broadcastReceiver, new IntentFilter("FILTER_ALARM_WEATHER"));
    }
}
