package com.bzt.bztviewandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bzt.bztviewandroid.R;
import com.bzt.bztviewandroid.interfaces.ButtonGroupClickListener;
import com.bzt.bztviewandroid.utils.DisplayUtils;

/**
 * 按钮集
 * Created by SHIBW-PC on 2015/12/2.
 */
public class ButtonGroup extends LinearLayout {

    private float textSize;
    private int textColor;

    private ButtonGroupClickListener mListener;

    private int itemCount = 0;
    public ButtonGroup(Context context) {
        this(context,null);
    }

    public ButtonGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
    }

    //为控件添加子view
    public ButtonGroup addItem(String text,int img,Context context){

        if (img != -1){
            //img != -1时，说明用户传了图片
            if (text.length() == 0){
                //只有图片
                ImageButton button = new ImageButton(context);
                button.setImageDrawable(context.getResources().getDrawable(img));
                putInViewGroup(button);
            }else {
                //有图片和文字
                Button button = new Button(context);

                button.setText(text);
                button.setTextSize(textSize);
                button.setTextColor(textColor);
                putInViewGroup(button);
                }
        }
        return this;
    }

    private void putInViewGroup(View view) {
        view.setBackground(null);
        LayoutParams params = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
        params.weight = 1.0f;
        view.setLayoutParams(params);
        itemCount++;
        final int index = itemCount-1;
        addView(view);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(index);
                }
            }
        });
    }

    public ButtonGroup addItem(int img,Context context){
        return addItem("",img,context);
    }

    public ButtonGroup addItem(String text,Context context){
        return addItem(text,-1,context);
    }

    public void setOnButtonGroupClickListener(ButtonGroupClickListener listener){
        mListener = listener;
    }

    /*
    初始化自定义属性
     */
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonGroup);

        textColor = ta.getColor(R.styleable.ButtonGroup_text_color,context.getResources().getColor(R.color.text_color));
        textSize = ta.getDimension(R.styleable.ButtonGroup_text_size, 14);

        textSize = DisplayUtils.px2sp(context, textSize);

        ta.recycle();
    }
}
