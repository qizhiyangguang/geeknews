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
import com.example.lenovo.geek.bean.DailyNewsBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class RlvDailyNewsAdapter extends RecyclerView.Adapter {
    private ArrayList<DailyNewsBean.StoriesBean> mNewsList;
    private ArrayList<DailyNewsBean.TopStoriesBean> mBanners;
    private Context context;
    private setOnClickListener sc;
    private String date = "今日新闻";

    public RlvDailyNewsAdapter(ArrayList<DailyNewsBean.StoriesBean> mNewsList, ArrayList<DailyNewsBean.TopStoriesBean> mBanners, Context context) {
        this.mNewsList = mNewsList;
        this.mBanners = mBanners;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.dailybanner, null);
            BannerHolder bannerHolder = new BannerHolder(inflate);
            return bannerHolder;
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.dailytext, null);
            TextHolder textHolder = new TextHolder(inflate);
            return textHolder;
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.dailyitem, null);
            ViewHolder viewHolder = new ViewHolder(inflate);
            return viewHolder;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        if (itemViewType == 0) {
            BannerHolder bannerHolder = (BannerHolder) holder;
            bannerHolder.banner.setImages(mBanners);
            bannerHolder.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    DailyNewsBean.TopStoriesBean bean = (DailyNewsBean.TopStoriesBean) path;
                    Glide.with(context).load(bean.getImage()).into(imageView);
                }
            }).start();
        } else if (itemViewType == 1) {
            TextHolder textHolder = (TextHolder) holder;
            textHolder.tv.setText(date);
        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            int newPosition = position - 1;
            if (mBanners.size() > 0) {
                newPosition -= 1;
            }
            DailyNewsBean.StoriesBean storiesBean = mNewsList.get(newPosition);
            Glide.with(context).load(storiesBean.getImages().get(0)).into(viewHolder.iv);
            viewHolder.tv.setText(storiesBean.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sc != null) {
                        sc.setClickListener(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (mBanners.size() > 0) {
            return mNewsList.size() + 1 + 1;
        } else {
            return mNewsList.size() + 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mBanners.size() > 0) {
            if (position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (position == 0) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    public void setAll(DailyNewsBean bean) {
        date = bean.getDate();
        mBanners.clear();
        if (bean.getTop_stories()!=null&&bean.getTop_stories().size()>0){
            mBanners.addAll(bean.getTop_stories());
        }
        mNewsList.clear();
        if (bean.getDate() != null && bean.getStories().size() > 0) {
            mNewsList.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }

    public static class BannerHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public Banner banner;

        public BannerHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.banner = (Banner) rootView.findViewById(R.id.banner);
        }

    }

    public static class TextHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;

        public TextHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView iv;
        public TextView tv;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.iv = (ImageView) rootView.findViewById(R.id.iv);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
        }

    }

    interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}
