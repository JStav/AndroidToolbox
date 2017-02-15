package com.stav.toolbox.base;


import android.os.Handler;

public class UpdateRunnable implements Runnable {

    private final long mTimeInterval;
    private final TimedTextView mTimedTextView;

    private TextFormatter mFormatter;
    private OnTickCallback mOnTickCallback;
    private Handler mHandler;

    public UpdateRunnable(long timeInterval, TimedTextView textView) {
        mTimedTextView = textView;
        mTimeInterval = timeInterval;
    }

    public void setOnTickCallback(OnTickCallback onTickCallback) {
        this.mOnTickCallback = onTickCallback;
    }

    public void setFormatter(TextFormatter formatter) {
        this.mFormatter = formatter;
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

        if(mFormatter != null) {
            mTimedTextView.setText(mFormatter.format());
        }

        if(mOnTickCallback != null) {
            mOnTickCallback.onTick();
        }

        if(mHandler != null) {
            mHandler.postDelayed(this, mTimeInterval);
        }
    }
}
