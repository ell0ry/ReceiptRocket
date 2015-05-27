package objects;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by ThomasHerring on 2015-05-02.
 */
public class Tag implements Serializable {

    String name;
    String color;

    public Tag(String name, String color) {
        this.color = color;
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
        color = chooseRandomColor();
    }

    private static String chooseRandomColor() {

        String ranColor = "";

        //Uses 9 standard colors
        int random = (int) (Math.random() * 9) + 1;

        switch (random) {
            case 1:
                ranColor = "red";
                break;
            case 2:
                ranColor = "blue";
                break;
            case 3:
                ranColor = "green";
                break;
            case 4:
                ranColor = "cyan";
                break;
            case 5:
                ranColor = "magenta";
                break;
            case 6:
                ranColor = "yellow";
                break;
            case 7:
                ranColor = "aqua";
                break;
            case 8:
                ranColor = "purple";
                break;
            case 9:
                ranColor = "teal";
                break;
        }
        return ranColor;

    }

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeChars(name);
        out.writeChars(color);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        //Check if this is the right input method for the name and color
        name = (String) in.readObject();
        color = (String) in.readObject();
    }

}


