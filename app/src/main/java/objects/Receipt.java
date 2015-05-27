package objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ThomasHerring on 2015-05-02.
 */
public class Receipt implements Parcelable, Serializable {

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Receipt> CREATOR = new Parcelable.Creator<Receipt>() {
        public Receipt createFromParcel(Parcel in) {
            return new Receipt(in);
        }

        public Receipt[] newArray(int size) {
            return new Receipt[size];
        }
    };


    double value;
    ArrayList<Tag> tagList;
    int year;
    int month;
    int day;

    public Receipt (double val, ArrayList<Tag> tagList){
        value = val;
        this.tagList = new ArrayList<Tag>(tagList);
    }

    public Receipt(double val, int year, int month, int day) {

        this.year = year;
        this.month = month;
        this.day = day;

        value = val;
    }


    private Receipt(Parcel in) {
        value = in.readDouble();
    }

    public String toString() {
        //Add one to month because 0 is Jan
        return "$" + value + " billed on " + year + "/" + (month + 1) + "/" + day;
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

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeDouble(value);
        out.write(tagList.size());
        for (int i = 0; i < tagList.size(); i++) {
            out.writeObject(tagList.get(i));
        }

    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        value = in.read();
        int listSize = in.read();
        tagList = new ArrayList<Tag>();
        for (int i = 0; i < listSize; i++) {
            tagList.add((Tag) in.readObject());
        }

    }

}
