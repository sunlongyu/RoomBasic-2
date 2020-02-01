package com.example.roombasic;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {
    public static WordDataBase INSTANCE;

    public static synchronized WordDataBase getWordDataBase(Context context) {
        if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database").build();
        }
        return INSTANCE;
    }

    public abstract WordDao getWordDao();
}
