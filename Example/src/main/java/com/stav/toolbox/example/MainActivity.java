package com.stav.toolbox.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stav.toolbox.util.TimeAgoTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TimeAgoTextView tvTimeAgo = (TimeAgoTextView) findViewById(R.id.tv_timed);
        Button btnStart = (Button) findViewById(R.id.btn_start_updates);
        Button btnStop = (Button) findViewById(R.id.btn_stop_updates);
        Button btnResetTime = (Button) findViewById(R.id.btn_reset_time);

        if(btnStart != null) {
            btnStart.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(tvTimeAgo != null) {
                        tvTimeAgo.startUpdates();
                    }
                }
            });
        }

        if(btnStop != null) {
            btnStop.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(tvTimeAgo != null) {
                        tvTimeAgo.stopUpdates();
                    }
                }
            });
        }

        if(btnResetTime != null) {
            btnResetTime.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(tvTimeAgo != null) {
                        tvTimeAgo.setTimestamp(System.currentTimeMillis());
                    }
                }
            });
        }
    }
}
