package com.stav.timedtextview.util;

import android.content.Context;
import android.util.AttributeSet;

import com.stav.timedtextview.base.TimedTextView;

/**
 * {@link com.stav.timedtextview.base.TextFormatter} Implementation that displays a
 * self updating "time ago" string.
 *
 * Defaults timestamp to current time, and interval to 1 second.
 */
public class TimeAgoTextView extends TimedTextView{

    private TimeAgoFormatter mTimeAgoFormatter;

    public TimeAgoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Set the formatter
        mTimeAgoFormatter = new TimeAgoFormatter(System.currentTimeMillis());
        setFormatter(mTimeAgoFormatter);
        setTimeInterval(1000); // 1 second
    }

    /**
     * Update timestamp to compare to
     * @param timestamp New timestamp
     */
    public void setTimestamp(long timestamp) {
        mTimeAgoFormatter.setTimestamp(timestamp);
    }
}
