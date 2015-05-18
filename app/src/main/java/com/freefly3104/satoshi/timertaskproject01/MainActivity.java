package com.freefly3104.satoshi.timertaskproject01;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    public static final long REPEAT_INTERVAL = 500;
    private TextView textView1;
    private int cnt = 0;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);

        // タイマー開始ボタンの処理
        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            timer = new Timer();
            TimerTask timerTask = new TimerTask1();
            timer.scheduleAtFixedRate(timerTask, REPEAT_INTERVAL, REPEAT_INTERVAL);

            }

        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelTimer();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }

    // タイマーをキャンセルする。
    private void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public class TimerTask1 extends TimerTask {

        private Handler handler = new Handler();

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView1.setText(String.valueOf(cnt));
                    cnt+=1;
                }
            });
        }

    }

}
