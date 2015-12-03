package com.bzt.bztview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bzt.bztviewandroid.interfaces.ButtonGroupClickListener;
import com.bzt.bztviewandroid.widget.ButtonGroup;

/**
 * Created by SHIBW-PC on 2015/12/2.
 */
public class ButtonGroupActivity extends AppCompatActivity {

    private ButtonGroup mGroup1,mGroup2;

    private int[] group1_img = {R.mipmap.ic_add_circle_outline_white_24dp,R.mipmap.ic_archive_white_24dp,R.mipmap.ic_drafts_white_24dp};

    private int[] group2_img = {R.mipmap.ic_add_circle_outline_white_24dp,R.mipmap.ic_archive_white_24dp,R.mipmap.ic_drafts_white_24dp,R.mipmap.ic_content_paste_white_24dp};
    private String[] group2_text = {"按钮1","按钮2","按钮3","按钮4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buttongroup);

        mGroup1 = (ButtonGroup) findViewById(R.id.button_group_horizontal);
        for ( int i = 0;i<group1_img.length;i++){
            mGroup1.addItem("",group1_img[i],ButtonGroupActivity.this);
        }
        mGroup1.setOnButtonGroupClickListener(new ButtonGroupClickListener() {
            @Override
            public void onClick(int index) {
                Toast.makeText(ButtonGroupActivity.this,"点击了第"+index+"个按钮",Toast.LENGTH_SHORT).show();
            }
        });

        mGroup2 = (ButtonGroup) findViewById(R.id.button_group_vertical);
        for ( int i = 0;i<group2_img.length;i++){
            mGroup2.addItem(group2_text[i],group2_img[i],ButtonGroupActivity.this);
        }
        mGroup2.setOnButtonGroupClickListener(new ButtonGroupClickListener() {
            @Override
            public void onClick(int index) {
                Toast.makeText(ButtonGroupActivity.this,"点击了"+group2_text[index],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
