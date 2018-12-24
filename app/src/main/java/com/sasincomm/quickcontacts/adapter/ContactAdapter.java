package com.sasincomm.quickcontacts.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sasincomm.quickcontacts.R;
import com.sasincomm.quickcontacts.base.BaseRecyclerViewAdapter;
import com.sasincomm.quickcontacts.base.BaseViewHolder;
import com.sasincomm.quickcontacts.model.Contact;

/**
 * 作者：wk on 2018/12/24.
 */
public class ContactAdapter extends BaseRecyclerViewAdapter {

    @Override
    public View getHolderView(ViewGroup parent, int position) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Contact contact=(Contact) _data.get(position);
        if(contact!=null)
        {
            //根据ListView的当前位置获取分类的首字母的char ascii值
            int section = contact.getSortKey().charAt(0);
            if (position == getPositionForSection(section)) {
                (holder.getView(R.id.item_contact_initials)).setVisibility(View.VISIBLE);
                ((TextView)holder.getView(R.id.item_contact_initials)).setText(((Contact)_data.get(position)).getSortKey());
            } else {
                (holder.getView(R.id.item_contact_initials)).setVisibility(View.GONE);
            }
            //显示联系人姓名
            ((TextView)holder.getView(R.id.item_contact_name)).setText((contact.getName()));
            //((CircleImageView)holder.getView(R.id.item_contact_image)).setImageBitmap();
        }
    }

    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    private int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = ((Contact)_data.get(i)).getSortKey();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
