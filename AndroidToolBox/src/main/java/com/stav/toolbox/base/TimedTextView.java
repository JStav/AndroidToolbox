package com.stav.toolbox.base;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TextView that updates on a specified time interval.
 * Text is updated based on the TextFormatter passed in to the TextView.
 * By default this TextView will not update, and will only begin to update
 * when it is passed in its time interval and TextFormatter
 */
public class TimedTextView extends TextView {

    private Handler mHandler;
    private UpdateRunnable mUpdateRunnable;
    private TextFormatter mFormatter;
    private long mTimeInterval;

    /**
     * {@inheritDoc}
     */
    public TimedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Sets the update time for the TextView
     * @param interval Time interval in milliseconds
     */
    public void setTimeInterval(long interval) {
        mTimeInterval = interval;
    }

    /**
     * Sets the text formatter for whenever this view updates
     * @param formatter {@link TextFormatter} for this TextView
     */
    public void setFormatter(TextFormatter formatter) {
        mFormatter = formatter;
    }

    /**
     * Start the timed updates. Will not start unless both the time interval and the formatter are set.
     */
    public void startUpdates() {
        if(mTimeInterval != 0 && mFormatter != null) {
            mHandler = new Handler();

            mUpdateRunnable = new UpdateRunnable(mTimeInterval, this, mFormatter);
            mUpdateRunnable.setHandler(mHandler);

            mHandler.postDelayed(mUpdateRunnable, mTimeInterval);
        }
    }

    /**
     * Stops timed updates. Does nothing if {@link #startUpdates()} hasn't been called yet.
     */
    public void stopUpdates() {
        if(mHandler != null && mUpdateRunnable != null) {
            mHandler.removeCallbacks(mUpdateRunnable);
        }
    }
}
