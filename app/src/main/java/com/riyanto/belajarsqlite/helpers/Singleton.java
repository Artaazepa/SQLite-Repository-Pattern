package com.riyanto.belajarsqlite.helpers;

import android.content.Context;

public class Singleton {

    public static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }
}
