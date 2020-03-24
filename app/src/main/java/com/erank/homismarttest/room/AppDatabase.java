package com.erank.homismarttest.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.erank.homismarttest.models.User;
import com.erank.homismarttest.utils.ActionTypeConverter;
import com.erank.homismarttest.utils.DateTypeConverter;

@Database(entities = {User.class, User.Record.class}, version = 1, exportSchema = false)
@TypeConverters({DateTypeConverter.class, ActionTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "HomiesDB";
    private static volatile AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            //Create database object
            INSTANCE = Room.databaseBuilder(context,
                    AppDatabase.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public abstract UserDao userDao();

    public abstract RecordDao recordDao();
}