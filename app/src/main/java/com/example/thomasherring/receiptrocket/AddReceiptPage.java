package com.example.thomasherring.receiptrocket;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import objects.Receipt;
import java.util.Calendar;


public class AddReceiptPage extends Activity {

    private double receiptValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt_page);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void onResume(){
        super.onResume();
        final AlertDialog.Builder keypadBuilder = new  AlertDialog.Builder(this);
        getDialogEnteredValue();

//        keypadBuilder.setCancelable(true);
//        keypadBuilder.setView(R.layout.receipt_enter_keypad);
//
//        keypadBuilder.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                        Intent intent = new Intent();
//                        setResult(RESULT_CANCELED, intent);
//                        finish();
//                    }
//                });
//
//        //Add amount to current receipt
//        keypadBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                Dialog d = (Dialog) dialog;
//                TextView keypadValue = (TextView)d.findViewById(R.id.keypadText);
//                receiptValue = Double.parseDouble(keypadValue.getText().toString());
//                dialog.cancel();
//            }
//        });
//
//
//
//        AlertDialog keypad = keypadBuilder.create();
//        keypad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        keypad.show();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void getDialogEnteredValue(){


        final AlertDialog.Builder keypadBuilder = new  AlertDialog.Builder(this);
        keypadBuilder.setCancelable(true);
        keypadBuilder.setView(R.layout.receipt_enter_keypad);

        keypadBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent();
                        setResult(RESULT_CANCELED, intent);
                        finish();
                    }
                });

        //Add amount to current receipt
        keypadBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Dialog d = (Dialog) dialog;
                TextView keypadValue = (TextView)d.findViewById(R.id.keypadText);
                receiptValue = Double.parseDouble(keypadValue.getText().toString());
                dialog.cancel();
            }
        });



        AlertDialog keypad = keypadBuilder.create();
        keypad.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        keypad.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_receipt_page, menu);
        return true;
    }

    public void receiptComplete(View view){
        Calendar cal =  Calendar.getInstance();
        EditText value = (EditText)findViewById(R.id.receiptValue);
        double enteredValue = Double.parseDouble( value.getText().toString());
        Intent intent = new Intent();
        intent.putExtra("enteredReceiptValue", enteredValue);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
