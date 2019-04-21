package com.example.lenovo.geek.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lenovo.geek.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class V2exShowActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TextView mTv1;
    private static final String TAG = "JieDianActivity";
    private String url = "https://www.v2ex.com/";
    private ImageView mIv;
    /**
     * 节点导航
     */
    private TextView mTv;
    private RelativeLayout mRlv1;
    /**
     * iOS
     */
    private TextView mTv2;
    private RelativeLayout mRlv2;
    /**
     * 品牌
     */
    private TextView mTv3;
    private RelativeLayout mRlv3;
    /**
     * 城市
     */
    private TextView mTv4;
    private RelativeLayout mRlv4;
    /**
     * 游戏
     */
    private TextView mTv5;
    private RelativeLayout mRlv5;
    /**
     * 生活
     */
    private TextView mTv6;
    private RelativeLayout mRlv6;
    /**
     * Geek
     */
    private TextView mTv7;
    private RelativeLayout mRlv7;
    /**
     * V2EX
     */
    private TextView mTv8;
    private RelativeLayout mRlv8;
    /**
     * Apple
     */
    private TextView mTv9;
    private RelativeLayout mRlv9;
    /**
     * Internet
     */
    private TextView mTv10;
    private RelativeLayout mRlv10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v2ex_show);
        initView();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mTv1 = (TextView) findViewById(R.id.tv1);
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);
        mRlv1 = (RelativeLayout) findViewById(R.id.rlv1);
        mTv2 = (TextView) findViewById(R.id.tv2);
        mRlv2 = (RelativeLayout) findViewById(R.id.rlv2);
        mTv3 = (TextView) findViewById(R.id.tv3);
        mRlv3 = (RelativeLayout) findViewById(R.id.rlv3);
        mTv4 = (TextView) findViewById(R.id.tv4);
        mRlv4 = (RelativeLayout) findViewById(R.id.rlv4);
        mTv5 = (TextView) findViewById(R.id.tv5);
        mRlv5 = (RelativeLayout) findViewById(R.id.rlv5);
        mTv6 = (TextView) findViewById(R.id.tv6);
        mRlv6 = (RelativeLayout) findViewById(R.id.rlv6);
        mTv7 = (TextView) findViewById(R.id.tv7);
        mRlv7 = (RelativeLayout) findViewById(R.id.rlv7);
        mTv8 = (TextView) findViewById(R.id.tv8);
        mRlv8 = (RelativeLayout) findViewById(R.id.rlv8);
        mTv9 = (TextView) findViewById(R.id.tv9);
        mRlv9 = (RelativeLayout) findViewById(R.id.rlv9);
        mTv10 = (TextView) findViewById(R.id.tv10);
        mRlv10 = (RelativeLayout) findViewById(R.id.rlv10);
        mToolBar.setTitle("");
        setSupportActionBar(mToolBar);
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Document doc = Jsoup.connect(url).get();
                    Elements items = doc.select("div.cell");
                    for (Element element : items) {
                        Elements tv = element.select("table tbody tr td > span.fade");
                        if (tv != null) {
                            String title = tv.text();
                            Log.d(TAG, "initData: " + title);
                        }
                        Elements wenben = element.select("table tbody tr td > a");
                        if (wenben != null) {
                            String attr = wenben.attr("href");
                            String text = wenben.text();
                            Log.d(TAG, "run: " + text);
                            Log.d(TAG, "attr: " + attr);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
