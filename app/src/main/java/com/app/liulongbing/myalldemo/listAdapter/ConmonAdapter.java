package com.app.liulongbing.myalldemo.listAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.app.liulongbing.myalldemo.R;

import java.util.List;

/**
 * Created by liulongbing on 16/12/27.
 */

public abstract class ConmonAdapter<T> extends BaseAdapter{

    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflate;
    protected int mLayoutId;



    public ConmonAdapter(Context context , List<T> datas,int layoutId){

        this.mContext = context;

        mInflate = LayoutInflater.from(context);

        this.mDatas = datas;

        this.mLayoutId = layoutId;

    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = ViewHolder.get(mContext,view,viewGroup, mLayoutId,i);

        convert(holder,getItem(i));

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder,T data);

}
