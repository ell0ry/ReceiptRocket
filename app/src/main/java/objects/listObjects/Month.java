package objects.listObjects;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;


/**
 * Created by ThomasHerring on 2015-05-08.
 */
public class Month {
    private ArrayList<Day> days;
    private int monthInYear;
    private int daysInMonth;
    private String monthName;

    public Month(int monthInYear) {

        //Determine how many days there are in the month based on current year
        Calendar myCal = Calendar.getInstance();
         daysInMonth = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);


        days  = new ArrayList<Day>();
        this.monthInYear = monthInYear;
        myCal.set(Calendar.MONTH, monthInYear);
        monthName = myCal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
        //Add one day because first day is not 0, day 0 will remain null
        days.add(null);
        for(int i = 1; i <= daysInMonth; i++){
            days.add(new Day(i));
        }
    }

    public Month(int monthInYear, int year) {

        //Determine how many days there are in the month based on input year
        Calendar myCal = new GregorianCalendar(year, monthInYear, 1);
        int daysInMonth = myCal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //Add one because first day is not 0
        days  = new ArrayList<Day>();
        this.monthInYear = monthInYear;
        monthName = myCal.getDisplayName(monthInYear, Calendar.LONG, Locale.US);

        //Add one day because first day is not 0, day 0 will remain null
        days.add(null);
        for(int i = 1; i <= daysInMonth; i++) {
            days.add(new Day(i));
        }
    }

    public Day get(int dayInMonth){
        return days.get(dayInMonth);
    }

    public void setDay(int day, Day d) {
        days.set(day, d);
    }

    public String getMonthName() {
        return monthName;
    }

    public int getDaysInMonth() {
        return daysInMonth;
    }


    public int getMonthInYear() {
        return monthInYear;
    }
}
