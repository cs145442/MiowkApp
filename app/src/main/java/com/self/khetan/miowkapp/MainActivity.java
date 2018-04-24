package com.self.khetan.miowkapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Interpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //find the view that shows the number categary
        TextView numbers = (TextView) findViewById(R.id.numbersActivityTV);
        TextView phrases = (TextView) findViewById(R.id.phrasesActivityTV);
        TextView family = (TextView) findViewById(R.id.familyActivityTV);
        TextView colors = (TextView) findViewById(R.id.colorsActivityTV);

        //Set clickListener for numbers
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To intent the view to layout
                Intent gotoNumbers = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(gotoNumbers);
            }
        });

        //set clickListeners for phrases
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To intent to the view of the layout
                Intent gotoPhrases = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(gotoPhrases);
            }
        });

        //set clickListeners for family
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoFamily = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(gotoFamily);
            }
        });

        //set clickListeners for colors
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoColors = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(gotoColors);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
