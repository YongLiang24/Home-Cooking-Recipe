package com.yongliang.homerecipe.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yongliang.homerecipe.R;
import com.yongliang.homerecipe.database.Repository;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Repository rep = new Repository(getApplication());
        System.out.println(rep.getAllSteps());
    }
}