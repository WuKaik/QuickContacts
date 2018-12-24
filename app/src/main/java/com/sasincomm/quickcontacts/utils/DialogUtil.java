package com.sasincomm.quickcontacts.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * 作者：wk on 2018/7/16.
 */
public class DialogUtil
{
    /**
     * 显示AlertDialog
     * @param context
     * @param title
     * @param message
     * @param cancelable
     * @param positiveText
     * @param negativeText
     * @param okListener
     */
    public static void showAlertDialog(Context context,int title,int message,boolean cancelable,
                                       int positiveText,int negativeText,
                                       DialogInterface.OnClickListener okListener)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.setPositiveButton(positiveText, okListener);
        dialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * 显示AlertDialog
     * @param context
     * @param title
     * @param message
     * @param cancelable
     * @param positiveText
     * @param negativeText
     * @param okListener
     * @param cancelListener
     */
    public static void showAlertDialog(Context context,int title,int message,boolean cancelable,
                                       int positiveText,int negativeText,
                                       DialogInterface.OnClickListener okListener,
                                       DialogInterface.OnClickListener cancelListener)
    {
        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(cancelable);
        dialog.setPositiveButton(positiveText, okListener);
        dialog.setNegativeButton(negativeText, cancelListener);
        dialog.show();
    }

    /**
     * 显示ProgressDialog
     * @param dialog
     * @param title
     * @param message
     * @param isBottom
     */
    public static void showProgressDialog(ProgressDialog dialog,String title,String message,boolean isBottom)
    {
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        if(isBottom)
        {
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        dialog.show();
    }

    /**
     * 显示ProgressDialog
     * @param dialog
     * @param title
     * @param message
     * @param y 距离底部的高度px
     */
    public static void showProgressDialog(ProgressDialog dialog,String title,String message,int y)
    {
        dialog.setTitle(title);
        dialog.setMessage(message);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        params.y = y;
        dialog.getWindow().setAttributes(params);
        dialog.show();
    }

    /**
     * 关闭ProgressDialog
     * @param dialog
     */
    public static void hideProgressDialog(ProgressDialog dialog)
    {
        if(dialog.isShowing())dialog.dismiss();
    }
}
