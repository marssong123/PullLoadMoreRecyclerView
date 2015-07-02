package com.wuxiaolong.pullloadmorerecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    int mCount = 1;
    RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setPullLoadMoreListener(new PullLoadMoreListener());
        setList();
    }

    private void setList() {
        List<String> dataList = new ArrayList<>();
        int start = 20 * (mCount - 1);
        for (int i = start; i < 20 * mCount; i++) {
            dataList.add("测试数据" + i);
        }
        mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
        if (mRecyclerViewAdapter == null) {
            mRecyclerViewAdapter = new RecyclerViewAdapter(this, dataList);
            mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        } else {
            mRecyclerViewAdapter.getDataList().addAll(dataList);
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
    }

    class PullLoadMoreListener implements PullLoadMoreRecyclerView.PullLoadMoreListener {
        @Override
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mRecyclerViewAdapter = null;
                    mCount = 1;
                    setList();
                }
            }, 3000);
        }

        @Override
        public void onLoadMore() {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    mCount = mCount + 1;
                    setList();
                }
            }, 3000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}