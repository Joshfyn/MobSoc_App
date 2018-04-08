package com.example.joshuaadeegbe.mob_soc_app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WalkActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;

    private TextView countStep;
    public TextView mTvTime;
    private Button mbtnStart;
    private Button mbtnEnd;

    private Context mContect;
    private Chronometer mChronometer;
    private Thread mThreadChrono;

    Boolean StepisRunning;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        mContect = this;

        mTvTime = (TextView) findViewById(R.id.mTvTimeRead);
        mbtnStart = (Button) findViewById(R.id.mbtnStart);
        mbtnEnd = (Button) findViewById(R.id.mbtnEnd);

        countStep = (TextView) findViewById(R.id.step_counter);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrometer_strt();
            }
        });

        mbtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrometer_stp();
            }
        });
	
    }

    public void chrometer_strt(){
        if (mChronometer == null ){
            mChronometer = new Chronometer(mContect);
            mThreadChrono = new Thread(mChronometer );
            mThreadChrono.start();
            mChronometer.Start_Chronometer();

        }
    }

    public void chrometer_stp(){
        if (mChronometer != null ) {
            mChronometer.stop_Chronometer();
            mThreadChrono.interrupt();
            mThreadChrono = null;
            mChronometer = null;

        }

    }

    public void updateTimeText(final String time){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvTime.setText(time);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        StepisRunning = true;
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (countSensor != null){
            sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else {
            Toast.makeText(this, "counter sensor not available!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        countStep.setText(String.valueOf(event.values[0]));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
