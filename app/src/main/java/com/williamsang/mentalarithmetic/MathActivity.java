package com.williamsang.mentalarithmetic;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import com.williamsang.mentalarithmetic.R;
import com.williamsang.mentalarithmetic.utils.Constants;

import static com.williamsang.mentalarithmetic.utils.Constants.getRandomSeqArray;

public class MathActivity extends AppCompatActivity {

    public static String SCORE = "SCORE";


    // 当前得分
    private int score = 0;
    private int result;
    private int questionCount = 0;

    private Random random = new Random();
    private int operatorButtonId = R.id.addButton;

    private TextView secondsTextView;
    private TextView scoreTextView;
    private TextView questionCountTextView;

    private TextView number1TextView;
    private TextView number2TextView;
    private TextView operatorTextView;

    private TextView result1TextView;
    private TextView result2TextView;
    private TextView result3TextView;
    private TextView result4TextView;

    private ArrayList<TextView> resultArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        this.secondsTextView = findViewById(R.id.remainSeconds);
        this.scoreTextView = findViewById(R.id.score);
        this.questionCountTextView = findViewById(R.id.questionCount);

        this.number1TextView = findViewById(R.id.number1);
        this.number2TextView = findViewById(R.id.number2);
        this.operatorTextView = findViewById(R.id.operator);

        this.result1TextView = findViewById(R.id.result1);
        this.result2TextView = findViewById(R.id.result2);
        this.result3TextView = findViewById(R.id.result3);
        this.result4TextView = findViewById(R.id.result4);

        // 初始化题目
        resultArrayList.add(result1TextView);
        resultArrayList.add(result2TextView);
        resultArrayList.add(result3TextView);
        resultArrayList.add(result4TextView);

        // 设置初始时间
        secondsTextView.setText(Integer.toString(Constants.roundSeconds));
        scoreTextView.setText(Integer.toString(0));
        questionCountTextView.setText(Integer.toString(0));

        Intent intent = getIntent();
        this.operatorButtonId = intent.getIntExtra(OperatorSelectorActivity.Operator, R.id.addButton);

        // 一秒执行一次
        generateQuestion(this.operatorButtonId);
        generateResult(this.result);

        // TODO 改成 TimeUnit
        new CountDownTimer(Constants.roundSeconds * 1000, 1000) {

            @Override
            public void onTick(long l) {
                new ChangeRemainSeconds().execute(1);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent();
                intent.setClassName("com.williamsang.mentalarithmetic",
                        "com.williamsang.mentalarithmetic.ScoreActivity");
                intent.putExtra(SCORE, score);
                startActivity(intent);
            }
        }.start();
    }

    private void generateQuestion(int operator) {
        int number1 = random.nextInt(20);
        int number2 = random.nextInt(20);

        switch (operator) {
            case R.id.addButton:
                this.result = number1 + number2;
                operatorTextView.setText("+");
                break;
            case R.id.subtractButton:
                this.result = number1 - number2;
                operatorTextView.setText("-");
                break;
            case R.id.multipleButton:
                this.result = number1 * number2;
                operatorTextView.setText("x");
                break;
            case R.id.divideButton:
                operatorTextView.setText("/");
                // TODO 除法修正
                this.result = number1 / number2;
                break;
        }

        //

        number1TextView.setText(Integer.toString(number1));
        number2TextView.setText(Integer.toString(number2));
    }

    private void generateResult(int correctResult) {
        // 随机生成只包含 1-4 的数组
        ArrayList<Integer> randomQueue = getRandomSeqArray(0, 3);
        Log.d("队列", randomQueue.toString());

        ArrayList<Integer> resultRandomBase = getRandomSeqArray(-10, 10);

        for (Integer i : randomQueue) {
            TextView textView = resultArrayList.get(i);
            textView.setText(Integer.toString(correctResult + resultRandomBase.get(i)));
        }
        // 数字一个正确结果
        Log.d("队列", randomQueue.get(0).toString());
        resultArrayList.get(randomQueue.get(0)).setText(Integer.toString(correctResult));
    }

    public void processAnswer(View view) {
        Button button = (Button) view;
        String answer = button.getText().toString();
        if (Integer.toString(this.result) == answer) {
            this.score++;
        } else {
            // TODO 弹出错误框
        }
        this.questionCount++;
        this.scoreTextView.setText(Integer.toString(this.score));
        this.questionCountTextView.setText(Integer.toString(this.questionCount));

        generateQuestion(this.operatorButtonId);
        generateResult(this.result);
    }

    private class ChangeRemainSeconds extends AsyncTask<Integer, Void, Void> {

        private int nextRemainSeconds;

        @Override
        protected Void doInBackground(Integer... integers) {
            Integer currentRemainSeconds;
            try {
                currentRemainSeconds = Integer.parseInt(secondsTextView.getText().toString());
            } catch (NumberFormatException e) {
                currentRemainSeconds = Constants.roundSeconds;
            }
            nextRemainSeconds = currentRemainSeconds - integers[0];
            // 如果小于等于0，设置回调 开启下一个activity，显示结果

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            secondsTextView.setText(Integer.toString(this.nextRemainSeconds));
        }
    }


}


