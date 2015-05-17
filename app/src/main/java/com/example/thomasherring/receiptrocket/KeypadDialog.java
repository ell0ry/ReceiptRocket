package com.example.thomasherring.receiptrocket;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.CharArrayWriter;

/**
 * Created by ThomasHerring on 2015-05-14.
 */
public class KeypadDialog extends AlertDialog{

    String beforeDecimal;
    String afterDecimal;
    TextView displayValue;
    boolean decimal = false;

    public KeypadDialog(Context c){
        super(c);
        beforeDecimal = "0";
        afterDecimal = "";
    }

  public View onCreatePanelView(int featureId) {

     /* Button keypadDot = (Button) findViewById(R.id.keyButtonDot);
      addButtonValue(keypadDot);*/
      Button keypadOne = (Button) findViewById(R.id.keyButton1);
      keypadOne.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View view) {

                  beforeDecimal = beforeDecimal + "1";
              String toDisplay = beforeDecimal + "." + afterDecimal;
              displayValue.setText(toDisplay);


          }

      });

   /*   Button keypadTwo = (Button) findViewById(R.id.keyButton2);
      addButtonValue(keypadTwo);
      Button keypadThree = (Button) findViewById(R.id.keyButton3);
      addButtonValue(keypadThree);
      Button keypadFour = (Button) findViewById(R.id.keyButton4);
      addButtonValue(keypadFour);
      Button keypadFive = (Button) findViewById(R.id.keyButton5);
      addButtonValue(keypadFive);
      Button keypadSix = (Button) findViewById(R.id.keyButton6);
      addButtonValue(keypadSix);
      Button keypadSeven = (Button) findViewById(R.id.keyButton7);
      addButtonValue(keypadSeven);
      Button keypadEight = (Button) findViewById(R.id.keyButton8);
      addButtonValue(keypadEight);
      Button keypadNine = (Button) findViewById(R.id.keyButton9);
      addButtonValue(keypadNine);*/

      displayValue = (TextView)findViewById(R.id.keypadValue);
      String toDisplay = beforeDecimal + "." + afterDecimal;
      displayValue.setText(toDisplay);


      return null;


  }

    public void addButtonValue(Button b) {
        //Final because buttonText has to be passed through into the onClick method
        final String buttonText = b.getText().toString();
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (buttonText.equals(".")) {
                    decimal = true;
                } else if (!decimal) {
                    beforeDecimal = beforeDecimal + buttonText;
                } else if (decimal) {
                    afterDecimal = afterDecimal + buttonText;
                }
            }

        });
    }



}
