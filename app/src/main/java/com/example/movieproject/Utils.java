package com.example.movieproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class Utils {

    public static Map<String,Object> toMap (JSONObject object) throws JSONException {
        Map<String,Object> map = new HashMap<>();
        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if (value instanceof JSONArray){
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject){
                value = toMap ((JSONObject)value);
            }
            map.put(key, value);

        }
        return map;
    }


    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                Object value = array.get(i);
                if (value instanceof JSONArray) {
                    value = toList((JSONArray) value);
                } else if (value instanceof JSONObject) {
                    value = Utils.toMap((JSONObject) value);
                }
                list.add(value);
            }
        } catch (Exception ex) {
            Log.e("Exception", ex.getMessage());
        }
        return list;
    }
}
