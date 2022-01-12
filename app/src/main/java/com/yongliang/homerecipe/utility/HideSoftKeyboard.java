package com.yongliang.homerecipe.utility;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AppCompatActivity;

//hides the soft keyboard when method is called.
public class HideSoftKeyboard  extends AppCompatActivity{

    public static void hideKeyboard(Context context, View v){
        if (v != null){
            InputMethodManager kb = (InputMethodManager)
            context.getSystemService(Context.INPUT_METHOD_SERVICE);
            kb.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
