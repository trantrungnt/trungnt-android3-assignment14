# trungnt-android3-assignment14

##Yêu cầu
+ [Cái tiến bài The Weather sử dụng api dữ liệu Thời tiết Hà Nội và có lưu vào SQL Lite](https://github.com/trantrungnt/LearnTheWeather)
+ App của mình mỗi ngày sau khi lấy dữ liệu mới về thì xóa toàn bộ dữ liệu cũ hơn 1 ngày.(dữ liệu lấy từ api đọc mới xong thì lưu vào SQL Lite)

##Kiến thức cần để làm
+ [SQLite Basic](https://youtu.be/LS2fBgUiOSI)

![SQLLite0](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%200_zpsgd8xvcqx.jpg)
![SQLLite1](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%201_zps9nqf19fr.jpg)
![SQLLite2](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%202_zpss9s5nksj.jpg)
![SQLLite3](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%203_zpsybwndnvv.jpg)
![SQLLite4](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%204_zpsb0pdz61p.jpg)
![SQLLite5](http://i477.photobucket.com/albums/rr132/trungepu/SQL%20Basic%205_zpsrradptrn.jpg)
![SQLLite6](http://i477.photobucket.com/albums/rr132/trungepu/truy%20van%20SQL_zpsrfchf6tr.jpg)

##Chú ý khi code
+ So sánh ngày hiện tại với ngày cần so sánh
```
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        simpleDateFormat.format(cal.getTime());
        Log.d("Current Time ", String.valueOf(simpleDateFormat.format(cal.getTime())));

        try {
            java.util.Date date1 = simpleDateFormat.parse("2016-01-12 03:56:50");
            java.util.Date date2 = simpleDateFormat.parse("2016-11-12 04:26:00");
            java.util.Date currentDate = new java.util.Date();

            if (date1.compareTo(currentDate)<0)
                Log.d("Message", "date1 before current date");

            if (date2.compareTo(currentDate)>0)
                Log.d("Message", "date2 after current date");

        } catch (ParseException e) {
            e.printStackTrace();
        }

```

+ Trong SQL
```
/*SELECT * FROM tblWeather where Create_at*/
select Datetime('now');
/*select * from tblWeather where Datetime('now') > Create_at;*/
/*select * from tblWeather where strftime('%H:%M:%S', Create_at ) > strftime('%H:%M:%S', '2016-05-31 03:56:50');*/
```

##Môi trường phát triển
+ Bộ công cụ Android Studio 2.1
+ Máy ảo Genymotion sử dụng api min 17 và max api 23

##Tham khảo
+ [Android insert datetime value in sqlite database](http://tips.androidhive.info/2013/10/android-insert-datetime-value-in-sqlite-database/)
+ [datatype3 in sqlite](http://www.sqlite.org/datatype3.html)
+ [ORMLite](http://ormlite.com/javadoc/ormlite-core/doc-files/ormlite_1.html#Getting-Started)
+ [Connect db SQLite to Android](http://developinginandroid.blogspot.com/2014/01/connect-db-sqlite-to-android-with.html)
+ [How do I see database tables in Android Studio](https://www.quora.com/How-do-I-see-database-tables-in-Android-Studio)
+ [Using your own sqlite database in Android application](http://blog.reigndesign.com/blog/using-your-own-sqlite-database-in-android-applications/)
+ [SQLite Open Helper with custom path](http://snippetrepo.com/snippets/sqliteopenhelper-with-custom-path)
+ [Android SQLite database](http://www.tutorialspoint.com/android/android_sqlite_database.htm)
+ [How to compare dates in Java](http://www.mkyong.com/java/how-to-compare-dates-in-java/)
+ [Compare Date Time in Java](http://www.tutorialspoint.com/java/java_date_time.htm)
+ [Alarms database - Alarm Manager](http://www.java2s.com/Open-Source/Android_Free_Code/Example/course/com_geoalarms_databaseAlarmManager_java.htm)
