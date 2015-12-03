package com.bzt.bztview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bzt.bztviewandroid.utils.CommonAdapter;
import com.bzt.bztviewandroid.utils.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

//测试通用适配器
public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mList;
    private List<Class<? extends AppCompatActivity>> activities;
    private CommonAdapter<String> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        activities = new ArrayList<>();
        mList = new ArrayList<>();
        //初始化数据
        initDates();
        mAdapter =new CommonAdapter<String>(MainActivity.this,mList,R.layout.item_mainactivity) {
            @Override
            protected void convertView(CommonViewHolder commonViewHolder, String s) {
                commonViewHolder.setText(R.id.classname,s);
            }
        };

        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,activities.get(position)));
            }
        });
    }

    private void initDates() {
        mList.add("ListViewActivity");
        activities.add(ListViewActivity.class);

        mList.add("SegmentLayoutActivity");
        activities.add(SegmentLayoutActivity.class);

        mList.add("ButtonGroupActivity");
        activities.add(ButtonGroupActivity.class);

        mList.add("PieChartActivity");
        activities.add(PieChartActivity.class);
    }
}
