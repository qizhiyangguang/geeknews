package com.example.lenovo.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lenovo.geek.R;
import com.example.lenovo.geek.bean.SectionsBean;

import java.util.ArrayList;
import java.util.List;

public class SectionsAdapter extends RecyclerView.Adapter {
    public ArrayList<SectionsBean.DataBean> list;
    private Context context;
    private setOnClickListener sc;

    public SectionsAdapter(ArrayList<SectionsBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sectionsitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        SectionsBean.DataBean dataBean = list.get(position);
        Glide.with(context).load(dataBean.getThumbnail()).into(viewHolder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sc.setClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setAll(List<SectionsBean.DataBean> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
        }

    }

    public interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}
