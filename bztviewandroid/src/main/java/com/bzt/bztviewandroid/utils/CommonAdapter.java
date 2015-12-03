package com.bzt.bztviewandroid.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用适配器
 * Created by SHIBW-PC on 2015/12/1.
 */
public abstract class CommonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> mList;
    private LayoutInflater mInflater;
    private int mLayoutId;
    public CommonAdapter(Context context, List<T> list, int layoutId)
    {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mList = list;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.get(mContext,convertView,parent,mLayoutId,position);

        convertView(commonViewHolder,getItem(position));

        return commonViewHolder.getConvertView();
    }

    protected abstract void convertView(CommonViewHolder commonViewHolder, T t);
}
