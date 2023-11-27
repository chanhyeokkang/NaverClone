package com.ck.naverclone;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LastActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("loginedId");
        final String pw = intent.getStringExtra("loginedPassword");

        tv1 = findViewById(R.id.textView25);
        tv2 = findViewById(R.id.textView26);
        tv3 = findViewById(R.id.textView27);

        tv1.setText("로그인 성공");
        tv2.setText("Id =" + id);
        tv3.setText("Pw =" + pw);


    }
}