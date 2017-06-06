package com.daiqile.ruidajinfu.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by orgwcl on 2016/9/2.
 */
public class ToastUtil {
    private static Toast toast = null;

    public static void showToast(Context mContext, String content) {
        if (toast == null) {
            toast = Toast.makeText(mContext, content, Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showToast(Context mContext, @StringRes int resId) {
        if (toast == null) {
            toast = Toast.makeText(mContext, resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }
}
