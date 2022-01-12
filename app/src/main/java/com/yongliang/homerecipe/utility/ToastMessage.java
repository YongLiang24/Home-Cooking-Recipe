package com.yongliang.homerecipe.utility;

import android.content.Context;
import android.widget.Toast;

public class ToastMessage {

    public static void showToast(Context context, String myMessage) {
        Toast.makeText(context, myMessage, Toast.LENGTH_LONG).show();
    }
}
