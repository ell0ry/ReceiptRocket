package objects.listObjects;


import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import objects.Receipt;


/**
 * Created by ThomasHerring on 2015-05-08.
 */
public class ReceiptList {

public static HashMap<Integer, Year> mainList = new HashMap<Integer, Year>();

    public static void addReceipt(int year, int month, int day, Receipt r) {
        try{
        mainList.get(year).getMonth(month).get(day).addReceipt(r);
        } catch(IndexOutOfBoundsException e){
            Log.d("CaughtException", "Error occurred in adding receipt \nTrace: " + e.getMessage());
        }
    }

    public static int getReceiptListSize(int year, int month, int day) {
        try{
            return mainList.get(year).getMonth(month).get(day).getReceiptList().size();
        } catch(IndexOutOfBoundsException e){
            Log.d("CaughtException", "Error occurred in accessing receiptList size \nTrace: " + e.getMessage());
        }
        return 0;
    }

    public static Receipt getReceipt(int year, int month, int day, int index) {
        try{
            return mainList.get(year).getMonth(month).get(day).getReceiptList().get(index);
        } catch(IndexOutOfBoundsException e){
            Log.d("CaughtException", "Error occurred in accessing receipt \nTrace: " + e.toString());
        }
        return null;
    }

    public static boolean hasReceipts(int year, int month, int day) {
        try{
            return mainList.get(year).getMonth(month).get(day).containsReceipts();
        } catch(IndexOutOfBoundsException e){
            Log.d("CaughtException", "Error occurred in determining is a receiptList has been init \nTrace: " + e.getMessage());
            return false;
        }

    }

 public static void addYearIf(int year){

     boolean contains = false;
     for(int i = 0; i < mainList.size(); i++){
         if(mainList.containsKey(year)){
             contains = true;
         }
     }

     if(!contains){
         mainList.put(Integer.valueOf(year), new Year(year));
     }

 }


}
