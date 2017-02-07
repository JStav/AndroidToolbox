package com.stav.toolbox.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.stav.toolbox.util.TimeAgoTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TimeAgoActivity extends AppCompatActivity {

    @BindView(R.id.btn_start_updates) Button mBtnStart;
    @BindView(R.id.btn_stop_updates) Button mBtnStop;
    @BindView(R.id.btn_reset_time) Button mBtnReset;
    @BindView(R.id.tv_timed) TimeAgoTextView mTvTimeAgo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_ago);
        ButterKnife.bind(this);
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
