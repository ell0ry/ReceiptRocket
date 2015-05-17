package objects;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ThomasHerring on 2015-05-02.
 */
public class Receipt implements Parcelable {

    double value;



    ArrayList<Tag> tagList;

    public Receipt (double val, ArrayList<Tag> tagList){
        value = val;
        this.tagList = new ArrayList<Tag>(tagList);
    }

    public Receipt (double val){

        value = val;
    }

    public String toString(){
        return "$" + value ;
    }




    /**
     * Methods used for passing Receipts in a parcelable format
     *
     */

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(value);


    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Receipt> CREATOR = new Parcelable.Creator<Receipt>() {
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };

    private Receipt(Parcel in) {
        value = in.readDouble();

    }


}
