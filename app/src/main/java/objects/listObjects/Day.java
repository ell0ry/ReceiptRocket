package objects.listObjects;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import objects.Receipt;

/**
 * Created by ThomasHerring on 2015-05-08.
 */
public class Day {
    private ArrayList<Receipt> receipts;
    private int dayInMonth;
    String dayInWeek;

    //ID system for receipts?

    public Day(int dayInMonth) {
        this.dayInMonth = dayInMonth;
        receipts = new ArrayList<Receipt>();
        //Gets the day of the month assuming that the day is in the current month and year
        Calendar tempCal = Calendar.getInstance();
        tempCal.set(Calendar.DAY_OF_MONTH, dayInMonth);
        dayInWeek = tempCal.getDisplayName(Calendar.DAY_OF_WEEK_IN_MONTH, Calendar.LONG, Locale.US);
    }

    public Day(int year, int monthInYear, int dayInMonth) {
        this.dayInMonth = dayInMonth;
        receipts = new ArrayList<Receipt>();
        //Gets the day of the month assuming that the day is in the current month and year
        Calendar tempCal = new GregorianCalendar(year, monthInYear, dayInMonth);
        dayInWeek = tempCal.getDisplayName(Calendar.DAY_OF_WEEK_IN_MONTH, Calendar.LONG, Locale.US);
    }

    public void addReceipt(Receipt r){
        receipts.add(r);
    }

    public ArrayList<Receipt> getReceiptList(){
        return receipts;
    }

    public boolean containsReceipts(){
        return !receipts.isEmpty();
    }

    public String getDayInWeek() {
        return dayInWeek;
    }

}
