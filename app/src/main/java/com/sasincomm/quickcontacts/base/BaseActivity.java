package com.sasincomm.quickcontacts.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：wk on 2018/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initWindows();
        super.onCreate(savedInstanceState);
        if (initArgs(getIntent().getExtras()))
        {
            setContentView(getContentLayoutId());
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            initWidget();
            initEvent();
            initData();
        }
        else
        {
            finish();
        }
    }

    protected void initWindows(){}

    protected boolean initArgs(Bundle bundle)
    {
        return true;
    }

    protected abstract int getContentLayoutId();

    protected abstract void initWidget();

    protected abstract void initEvent();

    protected abstract void initData();

}
