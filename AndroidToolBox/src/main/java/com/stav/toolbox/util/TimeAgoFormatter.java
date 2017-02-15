package com.stav.toolbox.util;

import android.text.format.DateUtils;

import com.stav.toolbox.base.TextFormatter;

/**
 * {@link TextFormatter} implementation that displays "x ago"
 * formatting
 */
public class TimeAgoFormatter implements TextFormatter {

    private long mTimestamp;

    /**
     * @param mTimestamp Initial timestamp to compare "now" time to
     */
    public TimeAgoFormatter(long mTimestamp) {
        this.mTimestamp = mTimestamp;
    }

    /**
     * Sets the timestamp to be formatted
     * @param timestamp Epoch timestamp in milliseconds
     */
    public void setTimestamp(long timestamp) {
        mTimestamp = timestamp;
    }

  /**
   * @return Current timestamp updates are compared to
   */
  public long getTimestamp() {
        return mTimestamp;
    }

    /**
     * Compare the set timestamp to "now" and format a "time ago" string using DateUtils.
     * @return  "time ago" string or null if the timestamp is not set or 0. (results in TextView being blank)
     */
    @Override
    public String format() {
        if(mTimestamp != 0) {
            return DateUtils
                    .getRelativeTimeSpanString(
                            mTimestamp,
                            System.currentTimeMillis(),
                            DateUtils.SECOND_IN_MILLIS)
                    .toString();
        } else {
            return null;
        }
    }
}
