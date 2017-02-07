package com.stav.toolbox.base;


import android.os.Handler;

public class UpdateRunnable implements Runnable {

    private final long mTimeInterval;
    private final TimedTextView mTimedTextView;
    private final TextFormatter mFormatter;
    private Handler mHandler;

    public UpdateRunnable(long timeInterval, TimedTextView textView, TextFormatter formatter) {
        mTimedTextView = textView;
        mTimeInterval = timeInterval;
        mFormatter = formatter;
    }

    /**
     * Sets the handler for this runnable. If not set, the update will only run once.
     * @param handler Handler
     */
    public void setHandler(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {

        mTimedTextView.setText(mFormatter.format());

        if(mHandler != null) {
            mHandler.postDelayed(this, mTimeInterval);
        }
    }
}
