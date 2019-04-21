package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.adapter.V2exAdapter;
import com.example.lenovo.geek.bean.V2exBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
//马畔畔-1808D

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exDetailFragment extends Fragment {

    private static final String TAG = "V2exDetailFragment";
    private String mUrl = "https://www.v2ex.com";
    private View view;
    private RecyclerView mRlv;
    private Document doc;
    private String mCommentCount;
    private String mHref;
    private String mAuthors;
    private ArrayList<V2exBean> beans;
    private V2exAdapter v2exAdapter;

    public V2exDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_v2ex_detail, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        beans = new ArrayList<>();
        Bundle arguments = getArguments();
      final String href =arguments.getString("href");
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    doc = Jsoup.connect(mUrl+href).get();
                    Elements items = doc.select("div.cell.item");
                    for (Element element : items) {
                        Element img = element.select("table tbody tr td > a > img.avatar").first();
                        String src = img.attr("src");
                        //评论，有可能没有，需要判断
                        Element comment = element.select("table tbody tr td >a.count_livid").first();
                        if (comment != null) {
                            mCommentCount = comment.text();
                            mHref = comment.attr("href");
                        }
                        //新闻的主题信息
                        Element titleElement = element.select("table tbody tr td span.item_title > a").first();
                        String title = titleElement.text();
                        //论评信息
                        Elements topicElement = element.select("table tbody tr td span.topic_info");
                        String topic = topicElement.text();
                        /*Element secondTab  = topicElement.select("a.node").first();
                        String text1 = secondTab.text();
                        Log.d(TAG, "二类的Tab:"+text1);*/
                        Elements people = topicElement.select("strong > a");
                        if (people.size() > 0) {
                            Element author = people.get(0);
                            mAuthors = author.text();
                            //Log.d(TAG, "作者: "+author.text());
                            //http://cdn.v2ex.com/avatar/f97f/701c/356016_normal.png?m=1555784674'
                        }
                        V2exBean v2exBean = new V2exBean("http:" + src, mAuthors, mCommentCount, mHref, topic, title);
                        beans.add(v2exBean);
                        Log.d(TAG, "run: " + beans.toString());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                v2exAdapter = new V2exAdapter(beans, getContext());
                                mRlv.setAdapter(v2exAdapter);
                                mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
                                v2exAdapter.notifyDataSetChanged();
                            }
                        });
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static Fragment newInstan(String link) {
        Bundle bundle = new Bundle();
        bundle.putString("href", link);
        V2exDetailFragment v2exDetailFragment = new V2exDetailFragment();
        v2exDetailFragment.setArguments(bundle);
        return v2exDetailFragment;
    }
}
