package com.williamsang.mentalarithmetic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();
        int score = intent.getIntExtra(MathActivity.SCORE, 0);

        TextView scoreTextView = findViewById(R.id.score);
        scoreTextView.setText("得分为：" + score);
    }
}
