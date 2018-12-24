package com.sasincomm.quickcontacts;

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
import com.sasincomm.quickcontacts.widget.SearchView;
import com.sasincomm.quickcontacts.widget.SliderView;

public class MainActivity extends BaseActivity {
    public static final String TAG="MainActivity";

    private SearchView searchView;
    private ImageView addContactIV;
    private SwipeRefreshLayout refreshLayout;
    private RecyclerView contactRV;
    private SliderView sliderView;
    private TextView showInitialsTV;

    private ContactAdapter mContactAdapter;

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
        mContactAdapter =new ContactAdapter();
        contactRV.setAdapter(mContactAdapter);
        contactRV.addItemDecoration(new DividerItemDecoration(MainActivity.this,DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void initEvent() {
        //快速查找联系人响应事件
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

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
                refreshLayout.setRefreshing(false);
            }
        });
        //联系人子项点击响应事件
        mContactAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

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
    }

}
