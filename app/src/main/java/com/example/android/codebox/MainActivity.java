package com.example.android.codebox;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private FloatingActionButton fab;
    private SimpleCursorAdapter dataAdapter;
    final Context context = this;
    EditText website, username, password, inputSearch;
    Button save;
    String record;
    ListView listview;
    public final static String EXTRA_MESSAGE = "com.example.android.codebox";

    DatabaseHandler databaseHandler, dbHandle ;
    SQLiteDatabase sqLiteDatabase, sqlDB;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    SearchView searchView;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //#####################ToolBar#########################
        setMyToolbar();
        //#####################ToolBar#########################


        listview = (ListView) findViewById(R.id.list_view);
        listDataAdapter = new ListDataAdapter(getApplicationContext(), R.layout.row_layout);
        listview.setAdapter(listDataAdapter);
        final ArrayList<String> list = new ArrayList<>();



        dbHandle = new DatabaseHandler(getApplicationContext());
        sqlDB = dbHandle.getReadableDatabase();
        cursor = dbHandle.getRecords(sqlDB);


        //####################Database Connectivity ##########################
        dbases();

        //#####################Floating Action Bar#########################
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Alertbox
                final StableArrayAdapter adapter = new StableArrayAdapter(context, android.R.layout.simple_list_item_1, list);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
                // ...Irrelevant code for customizing the buttons and title
                inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_capture, null);
                dialogBuilder.setView(dialogView);

                dialogBuilder.setTitle("Please enter the details")
                        .setMessage("Your credentials will be securely saved");

                website = (EditText) dialogView.findViewById(R.id.website);
                username = (EditText) dialogView.findViewById(R.id.username);
                password = (EditText) dialogView.findViewById(R.id.password);

                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
                //#####################Floating Action Bar#########################
            }
        });

    }

    public void setMyToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_poc);
        toolbar.setCollapsible(true);
        setSupportActionBar(toolbar);
    }

    public void dbases(){
        if(cursor.moveToFirst()){

            do{

                String web,user,pswd;
                web = cursor.getString(0);
                user = cursor.getString(1);
                pswd = cursor.getString(2);

                Post pst = new Post(web,user,pswd);
                listDataAdapter.add(pst);

            }while(cursor.moveToNext());
        }
    }

    public void addRecord(View view) {
        String mWebsite = website.getText().toString();
        String mUsername = username.getText().toString();
        String mPassword = password.getText().toString();

        databaseHandler = new DatabaseHandler(context);
        sqLiteDatabase = databaseHandler.getWritableDatabase();
        databaseHandler.addRecords(mWebsite, mUsername, mPassword, sqLiteDatabase);
        Toast.makeText(context, "Data Saved", Toast.LENGTH_SHORT).show();
        databaseHandler.close();



    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //*** setOnQueryTextFocusChangeListener ***
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String searchQuery) {
                listDataAdapter.filter(searchQuery.toString().trim());
                listview.invalidate();
                return true;
            }
        });

        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                // Do something when collapsed
                return true;  // Return true to collapse action view
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                // Do something when expanded
                return true;  // Return true to expand action view
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.action_search:
                Toast.makeText(this, R.string.search, Toast.LENGTH_LONG).show();
                return(true);
            case R.id.deleteAll:
                databaseHandler = new DatabaseHandler(context);
                sqLiteDatabase = databaseHandler.getWritableDatabase();
                databaseHandler.deleteRecords(sqlDB);
                listview.refreshDrawableState();
                Toast.makeText(this, R.string.delete, Toast.LENGTH_LONG).show();
                return(true);
            case R.id.exportAll:
                Toast.makeText(this, R.string.export, Toast.LENGTH_LONG).show();
                return(true);
            case R.id.exit:
                finish();
                return(true);

        }

        return(super.onOptionsItemSelected(item));
    }
}

class StableArrayAdapter extends ArrayAdapter<String> {

    ArrayList<String> data = new ArrayList<>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        this.data = (ArrayList)objects;
    }

    public void addItem(String item) {
        data.add(item);
        notifyDataSetChanged();
    }

}

