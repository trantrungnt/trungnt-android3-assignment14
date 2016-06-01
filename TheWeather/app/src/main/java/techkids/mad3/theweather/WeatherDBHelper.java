package techkids.mad3.theweather;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TrungNT on 5/30/2016.
 */
public class WeatherDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DB_NAME = "Weather.db";

    public static final String WEATHER_TABLE_NAME = "tblWeather";

    public static final String _ID = "id";
    public static final String CREATE_AT = "CREATE_AT";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String TEMPERATURE_MIN = "TEMPERATURE_MIN";
    public static final String TEMPERATURE_MAX = "TEMPERATURE_MAX";


    public static final int ID_INDEX = 0;
    public static final int CREATE_AT_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int TEMPERATURE_INDEX = 3;

    public WeatherDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_WEATHER_TABLE = "CREATE TABLE "
                + WEATHER_TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY NOT NULL, "
                + CREATE_AT + " DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + DESCRIPTION + " TEXT NOT NULL, "
                + TEMPERATURE_MIN + " FLOAT NOT NULL, "
                + TEMPERATURE_MAX + " FLOAT NOT NULL, "
                + TEMPERATURE + " FLOAT NOT NULL)";

        Log.d("sql onCreate", CREATE_WEATHER_TABLE);
        db.execSQL(CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WEATHER_TABLE_NAME);
        onCreate(db);
    }

    public List<Weather> getAllContacts() {
        List<Weather> contactList = new ArrayList<Weather>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + WEATHER_TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Weather weather = new Weather();
                weather.setId(Integer.parseInt(cursor.getString(0)));
                weather.setCreateAt(cursor.getString(CREATE_AT_INDEX));
                // Adding contact to list
                contactList.add(weather);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public int getWeatherCount() {
        int count;
        String countQuery = "SELECT * FROM " + WEATHER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }
}
