package objects;

/**
 * Created by ThomasHerring on 2015-05-02.
 */
public class Tag {

    String name;
    String c;

    public Tag(String n, String color) {
        this.c = color;
        name = n;
    }

    public Tag(String n) {
        name = n;
        c = chooseRandomColor();
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

}


