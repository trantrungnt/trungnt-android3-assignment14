package techkids.mad3.theweather;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TrungNT on 6/1/2016.
 */
public class StorageDataBroadCastReceive extends BroadcastReceiver {
    private WeatherDBHelper sqliteOpenDbHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;
    private Bundle getBundleData;
    private String minTemp, maxTemp, mainTemp, descriptionTemp;
    private SimpleDateFormat simpleDateFormat;
    private Date currentDate;
    private SQLiteDatabase db;

    @Override
    public void onReceive(Context context, Intent intent) {
        getBundleData = intent.getExtras();
        minTemp = getBundleData.getString("minTemp");
        maxTemp = getBundleData.getString("minTemp");
        mainTemp = getBundleData.getString("mainTemp");
        descriptionTemp = getBundleData.getString("descriptionTemp");

        currentDate = new Date();

        try{
            Cursor cursor = db.query(WeatherDBHelper.WEATHER_TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            while(cursor.moveToNext()) {
                long id = cursor.getLong(WeatherDBHelper.ID_INDEX);
                String create_at = cursor.getString(WeatherDBHelper.CREATE_AT_INDEX);
                Date getCreateAt = simpleDateFormat.parse(create_at);

                if (getCreateAt.compareTo(currentDate)<0)
                {
                    deleteWeaherByCurrentDateTime(context, id);
                }

            }

        }catch (ParseException e) {
            e.printStackTrace();
        }

        insertDataToSQLite(context, minTemp, maxTemp, mainTemp, descriptionTemp);


}

    //method sotorage data in SQLite
    private void insertDataToSQLite(Context context,
                                    String minTemp,
                                    String maxTemp,
                                    String mainTemp,
                                    String descriptionTemp)
    {
        sqliteOpenDbHelper = new WeatherDBHelper(context);
        database = sqliteOpenDbHelper.getWritableDatabase();

        try{
            contentValues = new ContentValues();
            contentValues.put(WeatherDBHelper.DESCRIPTION, descriptionTemp);
            contentValues.put(WeatherDBHelper.TEMPERATURE_MIN, minTemp);
            contentValues.put(WeatherDBHelper.TEMPERATURE_MAX, maxTemp);
            contentValues.put(WeatherDBHelper.TEMPERATURE, mainTemp);

            database.beginTransaction();
            database.insert(WeatherDBHelper.WEATHER_TABLE_NAME, null, contentValues);
            database.setTransactionSuccessful();
            database.endTransaction();

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            database.close();
        }
    }

    //Phuong thuc xoa du lieu theo ID
    private void deleteWeaherByCurrentDateTime(Context context, Long id)
    {
        sqliteOpenDbHelper = new WeatherDBHelper(context);
        database = sqliteOpenDbHelper.getWritableDatabase();
        database.delete(WeatherDBHelper.WEATHER_TABLE_NAME, "id = ?", new String[] {Long.toString(id)});
    }
}
