package com.sasincomm.quickcontacts.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public class BaseViewHolder extends RecyclerView.ViewHolder
{
    private SparseArray<View> views;
    private View convertView;

    public BaseViewHolder(View convertView)
    {
        super(convertView);
        views=new SparseArray<>();
        this.convertView=convertView;
    }

    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView() {
        return convertView;
    }
}
