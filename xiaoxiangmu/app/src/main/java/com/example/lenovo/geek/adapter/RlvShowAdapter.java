package com.example.lenovo.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.bean.GoldShowBean;
import com.example.lenovo.geek.widget.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

public class RlvShowAdapter extends RecyclerView.Adapter implements TouchCallBack {
    public ArrayList<GoldShowBean> list;
    private Context context;

    public RlvShowAdapter(ArrayList<GoldShowBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.showitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        final GoldShowBean goldShowBean = list.get(position);
        viewHolder.tv.setText(goldShowBean.title);
        viewHolder.sc.setChecked(goldShowBean.isChecked);
        viewHolder.sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                goldShowBean.isChecked = isChecked;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        //局部刷新,索引混乱
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        list.remove(position);
        //局部刷新,索引混乱,越界
        notifyItemRemoved(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public SwitchCompat sc;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.sc = (SwitchCompat) rootView.findViewById(R.id.sc);
        }

    }

}
