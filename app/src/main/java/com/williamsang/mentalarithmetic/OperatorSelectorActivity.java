package com.williamsang.mentalarithmetic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.williamsang.mentalarithmetic.R;

public class OperatorSelectorActivity extends AppCompatActivity {

    public final static String Operator = "Operator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_selector);
    }

    public void onClick(View button){
        Intent intent = new Intent(this, MathActivity.class);
        intent.putExtra(Operator, button.getId());
        startActivity(intent);
    }

}
