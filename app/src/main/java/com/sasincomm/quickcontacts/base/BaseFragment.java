package com.sasincomm.quickcontacts.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：wk on 2018/7/16.
 */
public abstract class BaseFragment extends Fragment
{
    private View mRoot;

    protected BaseActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(BaseActivity)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRoot == null){
            //初始化当前的根布局,但是不在创建时就添加到container里面
            mRoot = inflater.inflate(getContentLayoutId(), container, false);
            initWidget(mRoot);
            initEvent(mRoot);
            initData();
        }else {
            if (mRoot.getParent() != null){
                //把当前的根布局从其父控件中移除
                ((ViewGroup)mRoot.getParent()).removeView(mRoot);
            }
        }
        return mRoot;
    }

    /**
     * 获得当前界面的资源文件Id
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化界面
     * @param view
     */
    protected abstract void initWidget(View view);

    /**
     * 初始化事件
     * @param view
     */
    protected abstract void initEvent(View view);

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
