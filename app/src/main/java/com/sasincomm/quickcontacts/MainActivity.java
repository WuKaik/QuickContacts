package com.sasincomm.quickcontacts;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sasincomm.quickcontacts.adapter.ContactAdapter;
import com.sasincomm.quickcontacts.base.BaseActivity;
import com.sasincomm.quickcontacts.base.BaseRecyclerViewAdapter;
import com.sasincomm.quickcontacts.model.Contact;
import com.sasincomm.quickcontacts.utils.Logger;
import com.sasincomm.quickcontacts.widget.SearchView;
import com.sasincomm.quickcontacts.widget.SliderView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    public static final String TAG="MainActivity";
    private static final String PHONE_BOOK_LABLE="phonebook_label";

    private SearchView searchView;
    private ImageView addContactIV;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView contactRV;
    private SliderView sliderView;
    private TextView showInitialsTV;

    private ContactAdapter mContactAdapter;
    private List<Contact> mContactList=new ArrayList<>();
    private MyAsyncQueryHandler mQueryHandler;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        searchView=findViewById(R.id.searchView);
        addContactIV=findViewById(R.id.iv_contact_add);
        refreshLayout=findViewById(R.id.srl_contact);
        contactRV=findViewById(R.id.rv_contact);
        sliderView=findViewById(R.id.sliderView);
        showInitialsTV=findViewById(R.id.tv_contact_dialog);

        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        contactRV.setLayoutManager(manager);
        mContactAdapter =new ContactAdapter(MainActivity.this);
        contactRV.setAdapter(mContactAdapter);
        contactRV.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initEvent() {
        //注册联系人信息更新事件
        EventBus.getDefault().register(this);
        //快速查找联系人响应事件
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mContactAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //添加联系人响应事件
        addContactIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //下拉刷新响应事件
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateContactsInfo();
                refreshLayout.setRefreshing(false);
            }
        });
        //联系人子项点击响应事件
        mContactAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Contact contact=(Contact)mContactAdapter.getItem(position);
                ContactInfoActivity.jumpToContactInfoView(contact,MainActivity.this);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        //滑动条点击响应事件
        sliderView.setOnTouchingLetterChangedListener(new SliderView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {

            }
        });
    }

    @Override
    protected void initData() {
        sliderView.setTextView(showInitialsTV);
        mQueryHandler=new MyAsyncQueryHandler(getContentResolver());
        updateContactsInfo();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateContactInfoEvent(String event)
    {
        updateContactsInfo();
    }

    /**
     * 更新联系人信息
     */
    private void updateContactsInfo()
    {
        Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI; // 联系人Uri；
        // 查询的字段
        String[] phoneProjection = {
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,//联系人ID
                ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,//姓名
                ContactsContract.CommonDataKinds.Phone.DATA,//具体数据项
                PHONE_BOOK_LABLE,//首字母
                ContactsContract.CommonDataKinds.Phone.PHOTO_ID//图像ID
        };
        // 按照sort_key升序查詢
        mQueryHandler.startQuery(0, null, phoneUri, phoneProjection, null, null,
                ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
    }

    public class MyAsyncQueryHandler extends AsyncQueryHandler
    {
        public MyAsyncQueryHandler(ContentResolver resolver)
        {
            super(resolver);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            super.onQueryComplete(token, cookie, cursor);
            mContactList.clear();
            if (cursor != null && cursor.getCount() > 0)
            {
                cursor.moveToFirst();
                for (int i = 0; i < cursor.getCount(); i++)
                {
                    cursor.moveToPosition(i);
                    int contactId=cursor.getInt(0);
                    int rawId=cursor.getInt(1);
                    String name = cursor.getString(2);
                    String number = cursor.getString(3);
                    String sortKey = cursor.getString(4);
                    long photoId=cursor.getLong(5);
                    Logger.d(TAG,"联系人ID:"+contactId+" RawId:"+rawId+" 姓名:"+name+" 号码:"+number+" 首字母:"+sortKey);
                    // 创建联系人对象
                    Contact contact = new Contact();
                    contact.setId(contactId);
                    contact.setRawId(rawId);
                    contact.setName(name);
                    contact.setPhoneNum(number);
                    contact.setSortKey(sortKey);
                    contact.setPhotoId(photoId);
                    mContactList.add(contact);
                }
            }
            mContactAdapter.setData(mContactList);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
