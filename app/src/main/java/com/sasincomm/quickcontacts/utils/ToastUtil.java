package com.sasincomm.quickcontacts.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：wk on 2018/8/24.
 */
public class ToastUtil
{
    // 两次点击按钮之间的点击间隔不能少于2000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    private static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
            lastClickTime = curClickTime;
        }
        return flag;
    }

    /**
     * 显示系统样式Toast
     * @param context
     * @param messageStr
     * @param duration 1-显示时间长，其它值-显示时间短
     */
    public static void showToast(Context context,String messageStr,int duration)
    {
        if(duration==1)
        {
            if(isFastClick())
            {
                Toast.makeText(context,messageStr,Toast.LENGTH_LONG).show();
            }
        }else
        {
            if(isFastClick())
            {
                Toast.makeText(context,messageStr,Toast.LENGTH_SHORT).show();
            }
        }
    }

}
