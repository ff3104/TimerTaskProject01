package com.freefly3104.satoshi.timertaskproject01;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    private static final long REPEAT_INTERVAL = 100;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private Timer timer1;
    private Timer timer2;
    private Timer timer3;
    private int cnt = 0;

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

                // 0 ～ 9 の数値が入ったリストを作成
                List<Integer> list1 = new ArrayList();
                createList(list1);
                List<Integer> list2 = new ArrayList();
                createList(list2);
                List<Integer> list3 = new ArrayList();
                createList(list3);

                timer1 = new Timer();
                TimerTask timerTask1 = new TimerTask1(textView1, list1);
                timer1.scheduleAtFixedRate(timerTask1, REPEAT_INTERVAL, REPEAT_INTERVAL);

                timer2 = new Timer();
                TimerTask timerTask2 = new TimerTask1(textView2, list2);
                timer2.scheduleAtFixedRate(timerTask2, REPEAT_INTERVAL, REPEAT_INTERVAL);

                timer3 = new Timer();
                TimerTask timerTask3 = new TimerTask1(textView3, list3);
                timer3.scheduleAtFixedRate(timerTask3, REPEAT_INTERVAL, REPEAT_INTERVAL);

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

        Button btnStop3 = (Button)findViewById(R.id.btnStop3);
        btnStop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelTimer(timer3);
            }
        });
    }

    private void createList(List<Integer> list){
        for ( int i = 0; i < 10; i++ ) {
            list.add(i);
        }
        // 重複のない乱数にする為にシャッフルします
        Collections.shuffle(list);
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
        private List<Integer> list;

        public TimerTask1(TextView textView, List<Integer> list) {
            this.textView = textView;
            this.list = list;
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(list.get(cnt)));
                    cnt+=1;
                    if(cnt == 10){
                        cnt = 0;
                    }
                }
            });
        }

    }

}
