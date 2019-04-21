package com.example.lenovo.geek.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.geek.R;
import com.example.lenovo.geek.base.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoldDetailFragment extends Fragment {


    private View view;
    /**
     * geek
     */
    private TextView mTv;

    public GoldDetailFragment() {
        // Required empty public constructor
    }

    public static GoldDetailFragment newInstance(String text) {
        GoldDetailFragment fragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_gold_detail, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mTv = (TextView) inflate.findViewById(R.id.tv);
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        mTv.setText(data);
    }
}
