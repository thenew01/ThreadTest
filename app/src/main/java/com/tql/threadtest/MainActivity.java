package com.tql.threadtest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable, View.OnClickListener  {


    public static final int UPDATE_TEXT = 1;

    private TextView textView;

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("aaaaaaaaaaaaa");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        Button change_text = (Button)findViewById(R.id.change_text);
        change_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.change_text:
                {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //textView.setText("aaaaaaaaaaaa");
                        Message msg = new Message();
                        msg.what = UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            }
        }
    }

    @Override
    public void run() {

    }
}
