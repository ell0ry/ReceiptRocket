package com.example.thomasherring.receiptrocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import objects.Receipt;
import objects.listObjects.ReceiptList;

import java.util.ArrayList;
import java.util.Calendar;


public class MainPage extends Activity {

    public final int NUM_PAST_RECEIPTS = 10;


    ArrayList<Receipt> pastReceipts;
    ArrayList<String> pastReceiptsString;
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    Calendar currentCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Init main page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Init main list
        currentCal = Calendar.getInstance();
        ReceiptList.addYearIf(currentCal.get(Calendar.YEAR));

        //Init Lists
        pastReceipts = new ArrayList<Receipt>();

        //Setup list
        listView = (ListView) findViewById(R.id.listView);

        //Change in future to display the proper information about the list
        pastReceiptsString = convertToStringArray(pastReceipts);
        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                pastReceiptsString);

        listView.setAdapter(arrayAdapter);

    }

    protected void onResume() {

        super.onResume();

        Log.d("MainPage", "Updating calendar");
        currentCal = Calendar.getInstance();

    }


    private void updateListView() {

        pastReceipts = new ArrayList<Receipt>();

        //Search for the most recent NUM_PAST_RECEIPTS amount of receipts to display
        for (int m = currentCal.get(Calendar.MONTH); m >= 0; m--) {
            for (int d = currentCal.get(Calendar.DAY_OF_MONTH); d > 0; d--) {

                if (ReceiptList.hasReceipts(currentCal.get(Calendar.YEAR), m, d)) {

                    for (int i = ReceiptList.getReceiptListSize(currentCal.get(Calendar.YEAR), m, d) - 1; i >= 0; i--) {
                        if (pastReceipts.size() < NUM_PAST_RECEIPTS) {
                            pastReceipts.add(ReceiptList.getReceipt(currentCal.get(Calendar.YEAR), m, d, i));
                        }
                    }

                }
            }
        }

        pastReceiptsString = convertToStringArray(pastReceipts);

        arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                pastReceiptsString);

        listView.setAdapter(arrayAdapter);
        //arrayAdapter.notifyDataSetChanged();
    }


    private ArrayList<String> convertToStringArray(ArrayList<Receipt> arr) {
        ArrayList<String> stringArr = new ArrayList<String>();
        for (Receipt r : arr) {
            stringArr.add(r.toString());
        }
        return stringArr;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    public void addReceipt(View view) {

        Intent intent = new Intent(this, AddReceiptPage.class);
        startActivityForResult(intent, 0);
    }

    //Method not running when switching back from addReceiptPage
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Recieve text from add receipt page only if it complies with the right request code
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            double val = data.getDoubleExtra("enteredReceiptValue", 0);
            //Adds receipt for current day
            ReceiptList.addReceipt(currentCal.get(Calendar.YEAR), currentCal.get(Calendar.MONTH), currentCal.get(Calendar.DAY_OF_MONTH),
                    new Receipt(val));
            updateListView();
        }
        super.onActivityResult(requestCode, resultCode, data);
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

