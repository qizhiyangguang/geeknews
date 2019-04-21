package com.example.lenovo.geek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.geek.R;
import com.example.lenovo.geek.bean.WeChatBean;

import java.util.ArrayList;
import java.util.List;

public class WeChatAdapter extends RecyclerView.Adapter {
    public ArrayList<WeChatBean.NewslistBean> list;
    private Context context;
    private setOnClickListener sc;

    public WeChatAdapter(ArrayList<WeChatBean.NewslistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wechatitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        WeChatBean.NewslistBean newslistBean = list.get(position);
        Glide.with(context).load(newslistBean.getPicUrl()).into(viewHolder.iv);
        viewHolder.tv.setText(newslistBean.getTitle());
        viewHolder.tv1.setText(newslistBean.getDescription());
        viewHolder.tv2.setText(newslistBean.getCtime());
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

    public void setAll(List<WeChatBean.NewslistBean> newslist) {
        list.addAll(newslist);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;
        public TextView tv1;
        public TextView tv2;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
        }

    }

    public interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }

}
