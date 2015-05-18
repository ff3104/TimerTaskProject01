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
    private TextView textView2;
    private TextView textView3;
    private int cnt = 0;
    private Timer timer1;
    private Timer timer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        // タイマー開始ボタンの処理
        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                timer1 = new Timer();
                TimerTask timerTask1 = new TimerTask1(textView1);
                timer1.scheduleAtFixedRate(timerTask1, REPEAT_INTERVAL, REPEAT_INTERVAL);

                timer2 = new Timer();
                TimerTask timerTask2 = new TimerTask1(textView2);
                timer2.scheduleAtFixedRate(timerTask2, REPEAT_INTERVAL, REPEAT_INTERVAL);

            }

        });

        Button btnStop1 = (Button)findViewById(R.id.btnStop1);
        btnStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer(timer1);
            }
        });

        Button btnStop2 = (Button)findViewById(R.id.btnStop2);
        btnStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer(timer2);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer(timer1);
        cancelTimer(timer2);
    }

    // タイマーをキャンセルする。
    private void cancelTimer(Timer timer) {
        if (timer != null) {
            timer.cancel();
        }
    }

    public class TimerTask1 extends TimerTask {

        private Handler handler = new Handler();
        private TextView textView;

        public TimerTask1(TextView textView) {
            this.textView = textView;
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(cnt));
                    cnt+=1;
                }
            });
        }

    }

}
