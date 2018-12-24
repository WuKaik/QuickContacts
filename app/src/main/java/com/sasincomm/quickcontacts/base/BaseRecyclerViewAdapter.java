package com.sasincomm.quickcontacts.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder>
{
    protected List _data;

    public List getData() {
        return _data == null ? (_data = new ArrayList()) : _data;
    }

    public void setData(List data) {
        _data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (_data == null) {
            return 0;
        }
        return _data.size();
    }

    public Object getItem(int position) {
        if (_data != null && _data.size() > position) {
            return _data.get(position);
        }
        return null;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final BaseViewHolder holder = new BaseViewHolder(getHolderView(parent,viewType));
        //设置Item点击监听
        setOnClickListener(holder,parent,viewType);
        return holder;
    }

    protected void setOnClickListener(final BaseViewHolder holder, ViewGroup parent, int viewType)
    {
        if(onItemClickListener!=null)
        {
            holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,holder.getAdapterPosition());
                }
            });
            holder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(v,holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public abstract View getHolderView(ViewGroup parent, int position);


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
