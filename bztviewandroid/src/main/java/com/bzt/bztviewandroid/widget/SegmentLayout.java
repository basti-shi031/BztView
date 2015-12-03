package com.bzt.bztviewandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bzt.bztviewandroid.R;
import com.bzt.bztviewandroid.interfaces.SegmentLayoutClickListener;
import com.bzt.bztviewandroid.utils.DisplayUtils;

/**
 * 仿照iOS中 UiSegmentController
 * 类似于QQ 顶部 消息|电话 切换
 * Created by SHIBW-PC on 2015/12/2.
 */
public class SegmentLayout extends LinearLayout{

    private int normalTextColor,selectTextColor;
    private float normalTextSize,selectTextSize;
    private int normalBackgroundColor,selectBackgroundColor;

    private int itemCount = 0;
    private int selectIndex = 0;

    private SegmentLayoutClickListener mListener;

    public SegmentLayout(Context context) {
        this(context, null);
    }

    public SegmentLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SegmentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化自定义属性
        initAttr(context,attrs);
        
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
    }

    public void setOnItemClickListener(SegmentLayoutClickListener listener){
        mListener = listener;
    }

    public SegmentLayout addItem(Context context,String text){

        Button button = new Button(context);
        itemCount++;
        final int index = itemCount - 1;//item的index

        button.setText(text);
        button.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1));
        //将item加入控件中
        addViewWithStyle(index,button);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index != selectIndex) {
                    //点击未选中的item
                    //先将之前选中的item置为非选中状态
                    changeView((Button) getChildAt(selectIndex), false);
                    selectIndex = index;
                    //再讲现在选中的item置为选中状态
                    changeView((Button) getChildAt(selectIndex), true);
                    if (mListener!=null){
                        mListener.onItemClick(selectIndex);
                    }
                }
            }
        });

        return this;
    }

    private void addViewWithStyle(int index, Button button) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE); // 画框
        drawable.setStroke(1, selectBackgroundColor); // 边框粗细及颜色
        button.setBackgroundDrawable(drawable); // 设置背景（效果就是有边框及底色）

        if (index == 0){
            changeView(button, true);
        }else {
            changeView(button,false);
        }

        addView(button);
    }

    /*
    *  改变button的状态，
    *  params: isSelect 表示是否要将其选中
    * */
    private void changeView(Button button, boolean isSelect) {
        //从选中状态置为非选中状态 isSelect为false
        if (!isSelect){
            button.setTextSize(normalTextSize);
            button.setTextColor(normalTextColor);
            GradientDrawable drawable = (GradientDrawable) button.getBackground();
            drawable.setColor(normalBackgroundColor);
            button.setBackgroundDrawable(drawable);
        }else {
            //非选中状态置为选中状态，此时isSelect为true
            button.setTextSize(selectTextSize);
            button.setTextColor(selectTextColor);
            GradientDrawable drawable = (GradientDrawable) button.getBackground();
            drawable.setColor(selectBackgroundColor);
            button.setBackgroundDrawable(drawable);
        }
    }

    /*
    初始化自定义属性
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SegmentLayout);

        normalTextColor = ta.getColor(R.styleable.SegmentLayout_normal_text_color, context.getResources().getColor(R.color.normal_text_color));
        selectTextColor = ta.getColor(R.styleable.SegmentLayout_select_text_color, context.getResources().getColor(R.color.select_text_color));

        normalBackgroundColor  = ta.getColor(R.styleable.SegmentLayout_normal_background, context.getResources().getColor(R.color.normal_background_color));
        selectBackgroundColor = ta.getColor(R.styleable.SegmentLayout_select_background,context.getResources().getColor(R.color.select_background_color));

        normalTextSize =  ta.getDimension(R.styleable.SegmentLayout_normal_text_size, DisplayUtils.sp2px(context, 14));
        selectTextSize =  ta.getDimension(R.styleable.SegmentLayout_select_text_size, DisplayUtils.sp2px(context, 14));
        normalTextSize = DisplayUtils.px2sp(context,normalTextSize);
        selectTextSize = DisplayUtils.px2sp(context,selectTextSize);
        ta.recycle();
    }
}
