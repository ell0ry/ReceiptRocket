package objects;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by ThomasHerring on 2015-05-08.
 */
public class ReceiptList implements Serializable {

    private static ArrayList<Receipt> mainList = new ArrayList<Receipt>();
    private static Type type = new TypeToken<ArrayList<Receipt>>() {
    }.getType();


    public static void addReceipt(int year, int month, int day, Double val) {
        mainList.add(new Receipt(val, year, month, day));
    }

    public static int getReceiptListSize() {
        return mainList.size();
    }

    public static Receipt getReceipt(int index) {

        return mainList.get(index);
    }

    public static void saveList(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Log.d("ReceiptList", "Saving main list...");
        Gson gson = new Gson();
        String json = gson.toJson(mainList);
        prefsEditor.putString("ReceiptList", json);
        prefsEditor.apply();
    }


    public static void loadList(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Log.d("ReceiptList", "Loading main list...");
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("ReceiptList", "");
        mainList = gson.fromJson(json, type);
        if (mainList == null) {
            //Loaded a null list
            mainList = new ArrayList<Receipt>();
        }
    }


}
