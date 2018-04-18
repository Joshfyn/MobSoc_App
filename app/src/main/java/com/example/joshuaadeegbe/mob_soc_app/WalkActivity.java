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

public class    WalkActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private SensorManager sensorManager;
    private StepDetector simpleStepDetector;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = "Steps = ";
    private int numSteps;

    private TextView countStep;
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

        countStep = (TextView) findViewById(R.id.step_counter);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);


        mbtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrometer_strt();
                numSteps = 0;
                sensorManager.registerListener(WalkActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

            }
        });

        mbtnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chrometer_stp();
                sensorManager.unregisterListener(WalkActivity.this);
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
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        countStep.setText(TEXT_NUM_STEPS + numSteps);

    }
}
