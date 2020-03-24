package com.erank.homismarttest.utils;

import androidx.room.TypeConverter;

import com.erank.homismarttest.models.Action;

public class ActionTypeConverter {

    @TypeConverter
    public static Action toAction(int value) {
        return value == -1 ? null : Action.values()[value];
    }

    @TypeConverter
    public static int toInt(Action value) {
        return value == null ? -1 : value.ordinal();
    }
}