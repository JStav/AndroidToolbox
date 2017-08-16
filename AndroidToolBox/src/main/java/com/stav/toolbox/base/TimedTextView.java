package com.stav.toolbox.base;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/**
 * TextView that updates on a specified time interval.
 * Text is updated based on the TextFormatter passed in to the TextView.
 * By default this TextView will not update, and will only begin to update
 * when it is passed in its time interval and TextFormatter
 */
public class TimedTextView extends AppCompatTextView {

  private TextFormatter mFormatter;
  private OnTickCallback mOnTickCallback;
  private long mTimeInterval;

  private Disposable looperDisposable;

  /**
   * {@inheritDoc}
   */
  public TimedTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * Sets the update time for the TextView
   *
   * @param interval Time interval in milliseconds
   */
  public void setTimeInterval(long interval) {
    mTimeInterval = interval;
  }

  /**
   * Sets the text formatter for whenever this view updates
   *
   * @param formatter {@link TextFormatter} for this TextView
   */
  public void setFormatter(TextFormatter formatter) {
    mFormatter = formatter;
  }

  /**
   * Sets a callback for every tick of the timed TextView
   *
   * @param onTickCallback {@link OnTickCallback}
   */
  public void setOnTickCallback(OnTickCallback onTickCallback) {
    mOnTickCallback = onTickCallback;
  }

  private void doOnTick() {
    if (mFormatter != null) {
      setText(mFormatter.format());
      if (mOnTickCallback != null) {
        mOnTickCallback.onTick(mFormatter.getTimestamp());
      }
    }
  }

  /**
   * Start the timed updates. Will not start unless both the time interval and the formatter are
   * set.
   */
  public void startUpdates() {

    // Don't start multiple observables
    if (looperDisposable != null) {
      return;
    }

    looperDisposable = Observable.interval(0, mTimeInterval, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<Long>() {
          @Override public void accept(Long aLong) throws Exception {
            doOnTick();
          }
        });
  }

  /**
   * Stops timed updates. Does nothing if {@link #startUpdates()} hasn't been called yet.
   */
  public void stopUpdates() {
    if (looperDisposable != null) {
      looperDisposable.dispose();
      looperDisposable = null;
    }
  }
}
