package com.example.joshuaadeegbe.mob_soc_app;

import android.content.Context;

public class Chronometer implements Runnable {

    public static final long Mills_To_Minutes = 60000;
    public static final long Mills_To_Hours = 3600000;


    private Context mcontext;
    private long mstartTime;

    private boolean mIsRunning;

    public Chronometer(Context mcontext) {
        this.mcontext = mcontext;
    }

    public void Start_Chronometer(){
        mstartTime = System.currentTimeMillis();
        mIsRunning = true;

    }

    public void stop_Chronometer(){
        mIsRunning = false;

    }

    @Override
    public void run() {
        while (mIsRunning){
            long since = System.currentTimeMillis() - mstartTime;

            int seconds = (int) ((since / 1000) % 60);
            int minutes = (int) (((since / Mills_To_Minutes)) % 60);
            int hour = (int) ((since / (Mills_To_Hours))%24);
            int millis = (int) since % 1000;

            ((WalkActivity)mcontext).updateTimeText(String.format(
                    "%02dh:%02dmin:%02ds:%03d", hour, minutes, seconds, millis
            ));

            //Sleep the thread for a short amount, to prevent high CPU usage!
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
