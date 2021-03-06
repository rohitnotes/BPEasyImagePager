package com.iboism.bpeasyimagepager;

/**
 * Created by Brad on 3/8/2017.
 */

import android.os.Handler;

class PagerTimer implements Runnable {

    private Handler handler;
    private TimerListener timerListener;
    private boolean shouldRun = false;
    private long interval;

    public PagerTimer(int interval, TimerListener listener) {
        this.interval = interval;
        timerListener = listener;
        handler = new Handler();
    }

    public synchronized void startTimer() {
        if (!shouldRun) {
            shouldRun = true;
            handler.post(this);
        }
    }

    public synchronized void stopTimer() {
        if (shouldRun) {
            shouldRun = false;
            handler.removeCallbacks(this);
        }
    }

    @Override
    public synchronized void run() {
        if (shouldRun) {
            timerListener.onIntervalTick();
            handler.postDelayed(this, interval);
        }
    }

    public interface TimerListener {
        void onIntervalTick();
    }

}