package com.example.will.nhatro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class Trangchu extends ListActivity {
    protected List<ParseObject> mStatus; // mang objectparse
    protected Button mdangtin; // dang tim
    protected EditText timkiem; // tim kiem
    ArrayAdapter<ParseObject> aaa;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);


        timkiem = (EditText) findViewById(R.id.inputSearch);
        mdangtin = (Button) findViewById(R.id.dangtinbtn_button);
        mdangtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null) {
                    // do stuff with the user
                    Intent nextcapnhap = new Intent(Trangchu.this, CapnhapActivity.class);
                    startActivity(nextcapnhap);

                } else {
                    // show the signup or login screen
                    Toast.makeText(Trangchu.this, "Bạn phải đăng nhập trước khi đăng tin", Toast.LENGTH_LONG).show();
                    Intent nextlogin2 = new Intent(Trangchu.this, DangnhapActivity.class);
                    startActivity(nextlogin2);
                }

            }
        });

        /////------------------//
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Dulieu");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(final List<ParseObject> status, ParseException e) {
                if (e == null) {
                    mStatus = status;

                    final statusAdapter adapter = new statusAdapter(getListView().getContext(), mStatus);
                    setListAdapter(adapter);
                    timkiem.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            adapter.getFilter().filter(s);


                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    ///////////////////////////////


                } else {
                }


            }
        });
        //---------------------//


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_trangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.UpdateStatus:
            {

                ParseUser currentUser = ParseUser.getCurrentUser();
                if (currentUser != null)



                {
                    // do stuff with the user
                    Intent nextcapnhap = new Intent(Trangchu.this, CapnhapActivity.class);
                    startActivity(nextcapnhap);

                }
                else
                {
                        // show the signup or login screen
                    Toast.makeText(Trangchu.this,"Bạn phải đăng nhập trước khi đăng tin", Toast.LENGTH_LONG).show();
                        Intent nextlogin2 = new Intent(Trangchu.this, DangnhapActivity.class);
                        startActivity(nextlogin2);
                }

                break;
            }
            case R.id.LogoutUser:
            {
                ParseUser.logOut();
                Intent nextupdate = new Intent(this, DangnhapActivity.class);
                startActivity(nextupdate);
                break;

            }
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
        super.onListItemClick(l,v,position,id);
        ParseObject statusOb = mStatus.get(position);
        String objectId = statusOb.getObjectId();

       // Toast.makeText(getApplicationContext(), objectId, Toast.LENGTH_LONG).show();
        Intent mhchitiet = new Intent(this, ChitietActivity.class);
        mhchitiet.putExtra("objectID", objectId);
        startActivity(mhchitiet);

    }

}
