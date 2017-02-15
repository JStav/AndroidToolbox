package com.stav.toolbox.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;
import com.stav.toolbox.base.OnTickCallback;
import com.stav.toolbox.util.TimeAgoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeAgoActivity extends AppCompatActivity {

    @BindView(R.id.tv_timed) TimeAgoTextView mTvTimeAgo;
    @BindView(R.id.tv_on_tick) TextView mTvOnTick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_ago);
        ButterKnife.bind(this);

        mTvTimeAgo.setTimeInterval(5000);
        mTvTimeAgo.setOnTickCallback(new OnTickCallback() {
            @Override public void onTick() {
                String msg = "OnTick: " + System.currentTimeMillis();
                mTvOnTick.setText(msg);
            }
        });
    }

    @OnClick(R.id.btn_start_updates) void start() {
        mTvTimeAgo.startUpdates();
    }

    @OnClick(R.id.btn_stop_updates) void stop() {
        mTvTimeAgo.stopUpdates();
    }

    @OnClick(R.id.btn_reset_time) void reset() {
        mTvTimeAgo.setTimestamp(System.currentTimeMillis());
    }
}
