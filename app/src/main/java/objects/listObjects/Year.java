package objects.listObjects;

import java.util.ArrayList;

/**
 * Created by ThomasHerring on 2015-05-09.
 */
public class Year {

    private ArrayList<Month> months;
    private int year;

    public Year (int year){
        this.year = year;
        //First month is 0 in Calendar class
        months = new ArrayList<Month>();
        for(int i = 0; i < 12; i++){
            months.add(new Month(i));
        }
    }

    public Month getMonth (int monthInYear){
        return months.get(monthInYear);
    }


    public int getYear() {
        return year;
    }
}
