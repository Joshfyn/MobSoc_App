package com.example.joshuaadeegbe.mob_soc_app;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WalkActivity extends AppCompatActivity {
    public TextView mTvTime;
    private Button mbtnStart;
    private Button mbtnEnd;

    private Context mContect;
    private Chronometer mChronometer;
    private Thread mThreadChrono;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        mContect = this;

        mTvTime = (TextView) findViewById(R.id.mTvTimeRead);
        mbtnStart = (Button) findViewById(R.id.mbtnStart);
        mbtnEnd = (Button) findViewById(R.id.mbtnEnd);

        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mChronometer == null ){
                    mChronometer = new Chronometer(mContect);
                    mThreadChrono = new Thread(mChronometer );
                    mThreadChrono.start();
                    mChronometer.Start_Chronometer();

                }

            }
        });

        mbtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChronometer != null ) {
                    mChronometer.stop_Chronometer();
                    mThreadChrono.interrupt();
                    mThreadChrono = null;
                    mChronometer = null;

                }
            }
        });
	
    }

    public void updateTimeText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTime.setText(time);
            }
        });
    }
}
