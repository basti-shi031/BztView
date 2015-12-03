package com.bzt.bztview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bzt.bztview.bean.Student;
import com.bzt.bztviewandroid.utils.CommonAdapter;
import com.bzt.bztviewandroid.utils.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SHIBW-PC on 2015/12/2.
 */
public class ListViewActivity extends AppCompatActivity {
    private ListView mListView;
    private List<Student> mList;
    private CommonAdapter<Student> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);
        mList = new ArrayList<>();
        //初始化数据
        initDates();

        //适配器
        mAdapter = new CommonAdapter<Student>(this, mList,
                R.layout.item_student) {
            @Override
            protected void convertView(CommonViewHolder commonViewHolder, final Student student) {
                commonViewHolder.setText(R.id.name,student.getName())
                        .setText(R.id.numbertest, student.getNumber())
                        .setOnClick(R.id.name, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ListViewActivity.this, student.getName(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setOnClick(R.id.numbertest, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(ListViewActivity.this, student.getNumber(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        };

        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewActivity.this,"这是第"+position+"个Item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDates() {
        for (int i = 0; i<30;i++){
            mList.add(new Student("姓名：百智通"+i,"学号：100"+i));
        }
    }
}
