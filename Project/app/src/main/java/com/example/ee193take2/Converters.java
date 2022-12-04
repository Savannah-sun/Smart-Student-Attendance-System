package com.example.ee193take2;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.List;

public class Converters {

    @TypeConverter
    public static String fromBoolList(List<Boolean> boolList){
        Gson gson = new Gson();
        String json = gson.toJson(boolList);
        return json;

    }

    @TypeConverter
    public static List<Boolean> fromSerialBool(String boolList){
        Type listType = new TypeToken<List<Boolean>>() {}.getType();
        return new Gson().fromJson(boolList, listType);
    }


    @TypeConverter
    public static String fromStringList(List<String> stringList){
        Gson gson = new Gson();
        String json = gson.toJson(stringList);
        return json;

    }

    @TypeConverter
    public static List<String> fromSerialString(String stringList){
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(stringList, listType);
    }

}
