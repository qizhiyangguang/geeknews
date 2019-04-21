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
import com.example.lenovo.geek.bean.V2exBean;

import java.util.ArrayList;

public class V2exAdapter extends RecyclerView.Adapter {
    public ArrayList<V2exBean> list;
    private Context context;
    private setOnClickListener sc;

    public V2exAdapter(ArrayList<V2exBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.v2exitem, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        V2exBean v2exBean = list.get(position);
        Glide.with(context).load(v2exBean.getImg()).into(viewHolder.iv);
        viewHolder.tv.setText(v2exBean.getAuthor());
        viewHolder.tv1.setText(v2exBean.getHref());
        viewHolder.tv2.setText(v2exBean.getNumber());
        viewHolder.tv3.setText(v2exBean.getTopic());
        viewHolder.tv4.setText(v2exBean.getTitles());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv4;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tv1 = (TextView) rootView.findViewById(R.id.tv1);
            this.tv2 = (TextView) rootView.findViewById(R.id.tv2);
            this.tv3 = (TextView) rootView.findViewById(R.id.tv3);
            this.tv4 = (TextView) rootView.findViewById(R.id.tv4);
        }
    }

    interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}
