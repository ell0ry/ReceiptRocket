package com.example.thomasherring.receiptrocket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

import objects.Receipt;
import objects.ReceiptList;


public class MainPage extends Activity {

    private final int NUM_PAST_RECEIPTS = 10;
    private final String SAVE_NAME = "mainList";

    private ArrayList<Receipt> pastReceipts;
    private ArrayList<String> pastReceiptsString;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private Calendar currentCal;
    private ReceiptList mainList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Init main page
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //Init main list
        currentCal = Calendar.getInstance();


        //Load mainList
        ReceiptList.loadList(this.getApplicationContext());

        //Init Lists
        listView = (ListView) findViewById(R.id.listView);
        updateListView();

//        pastReceipts = new ArrayList<Receipt>();
//
//        //Setup list
//        listView = (ListView) findViewById(R.id.listView);
//
//        //Change in future to display the proper information about the list
//        pastReceiptsString = convertToStringArray(pastReceipts);
//        arrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1,
//                pastReceiptsString);
//
//        listView.setAdapter(arrayAdapter);

    }

    protected void onResume() {

        super.onResume();

        Log.d("MainPage", "Updating calendar");
        currentCal = Calendar.getInstance();

    }

    protected void onDestroy() {
        super.onDestroy();
        ReceiptList.saveList(this.getApplicationContext());

    }

    private void updateListView() {

        pastReceipts = new ArrayList<Receipt>();
        int mainListSize = ReceiptList.getReceiptListSize();

        //Search for the most recent NUM_PAST_RECEIPTS amount of receipts to display
        for (int i = mainListSize - 1; pastReceipts.size() < 10 && i >= 0; i--) {
            pastReceipts.add(ReceiptList.getReceipt(i));
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
                    val);

            updateListView();
            ReceiptList.saveList(this.getApplicationContext());
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

