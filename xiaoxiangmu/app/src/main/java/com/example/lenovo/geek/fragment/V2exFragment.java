package com.example.lenovo.geek.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.activity.V2exShowActivity;
import com.example.lenovo.geek.adapter.VpV2exAdapter;
import com.example.lenovo.geek.base.Constants;
import com.example.lenovo.geek.bean.GoldShowBean;
import com.example.lenovo.geek.bean.V2exTabBean;

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
public class V2exFragment extends Fragment {
    private static final String TAG = "V2exFragment";
    private View view;
    private TabLayout mTab;
    private ImageView mIv;
    private ViewPager mVp;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private String mUrl = "https://www.v2ex.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_v2ex, container, false);
        initView(inflate);

        getTab();
        return inflate;
    }

    private void getTab() {
        final ArrayList<V2exTabBean> tabBeans = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    //查找id是tabs的div元素，因为只有一个，直接调用了first();
                    Element tabs = document.select("div#Tabs").first();
                    //查找带有href属性的a元素
                    Elements select = tabs.select("a[href]");
                    for (Element element : select) {
                        String linlHref = element.attr("href");
                        String tab = element.text();
                        V2exTabBean v2exTabBean = new V2exTabBean(linlHref, tab);
                        tabBeans.add(v2exTabBean);
                        Log.d(TAG, "linkHref: " + v2exTabBean.toString());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragments = new ArrayList<>();
                            for (int i = 0; i < tabBeans.size(); i++) {
                                fragments.add(V2exDetailFragment.newInstan(tabBeans.get(i).getLink()));
                        }
                            VpV2exAdapter vpV2exAdapter = new VpV2exAdapter(getChildFragmentManager(), fragments, tabBeans);
                            mVp.setAdapter(vpV2exAdapter);
                            mTab.setupWithViewPager(mVp);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void initView(final View inflate) {
        mTab = (TabLayout) inflate.findViewById(R.id.tab);
        mIv = (ImageView) inflate.findViewById(R.id.iv);
        mVp = (ViewPager) inflate.findViewById(R.id.vp);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), V2exShowActivity.class);
                intent.putExtra(Constants.DATA, strings);
                startActivityForResult(intent, 100);
            }
        });
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (data != null) {
//            if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
//                strings = (ArrayList<GoldShowBean>) data.getSerializableExtra(Constants.DATA);
//                //刷新界面
//                setFragments();
//            }
//        }
//    }
}
