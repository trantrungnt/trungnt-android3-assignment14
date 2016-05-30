package techkids.mad3.theweather;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by TrungNT on 5/30/2016.
 */
public class WeatherDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 5;
    private static final String DB_NAME = "Weather.db";

    public static final String WEATHER_TABLE_NAME = "tblWeather";

    public static final String _ID = "id";
    public static final String CREATE_AT = "CREATE_AT";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String TEMPERATURE_MIN = "TEMPERATURE_MIN";
    public static final String TEMPERATURE_MAX = "TEMPERATURE_MAX";


    public static final int ID_INDEX = 0;
    public static final int DESCRIPTION_INDEX = 1;
    public static final int TEMPERATURE_INDEX = 2;

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
}
