package com.example.joshuaadeegbe.mob_soc_app;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Exercise_Option extends AppCompatActivity {
    private ImageView image1;
    private ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise__option);

        image1 = (ImageView) findViewById(R.id.walk_action);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_walkAction();

            }
        });

        image2 = (ImageView) findViewById(R.id.run_action);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Open_RunAction();

            }
        });
    }

    public void Open_walkAction() {
        Intent intent = new Intent(this, WalkActivity.class);
        startActivity(intent);
    }

    public void Open_RunAction() {
        Intent intent = new Intent(this, RunActivity.class);
        startActivity(intent);
    }




    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true; // change here, so it can launch the necessary activities
        }

        else if (id == R.id.action_faq){
            return true; // change here, so it can launch the necessary activities
        }
        else if (id == R.id.action_feedback){
            return true; // change here, so it can launch the necessary activities
        }

        return super.onOptionsItemSelected(item);
    }
}
