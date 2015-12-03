package com.bzt.bztview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.bzt.bztviewandroid.interfaces.SegmentLayoutClickListener;
import com.bzt.bztviewandroid.widget.SegmentLayout;

/**
 * Created by SHIBW-PC on 2015/12/2.
 */
public class SegmentLayoutActivity extends AppCompatActivity {

    private SegmentLayout segmentLayout,segmentLayout1;
    private String[] mlist1 = {"九年级一班","九年级二班","九年级三班"};
    private String[] mlist2 = {"百智通1","百智通2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segmentlayout);

        segmentLayout = (SegmentLayout) findViewById(R.id.segmentlayout);

        for (int i = 0;i<mlist1.length;i++){
            segmentLayout.addItem(this,mlist1[i]);
        }
        segmentLayout.setOnItemClickListener(new SegmentLayoutClickListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(getApplicationContext(), "点击了" + mlist1[index], Toast.LENGTH_SHORT).show();
            }
        });



        segmentLayout1 = (SegmentLayout) findViewById(R.id.segmentlayout1);
        for (int i = 0;i<mlist2.length;i++){
            segmentLayout1.addItem(this,mlist2[i]);
        }

        segmentLayout1.setOnItemClickListener(new SegmentLayoutClickListener() {
            @Override
            public void onItemClick(int index) {
                Toast.makeText(getApplicationContext(), "点击了" + mlist2[index], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
