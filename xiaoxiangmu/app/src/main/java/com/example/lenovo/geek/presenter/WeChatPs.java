package com.example.lenovo.geek.presenter;

import com.example.lenovo.geek.bean.WeChatBean;
import com.example.lenovo.geek.model.WeChatM;
import com.example.lenovo.geek.model.WeChatMs;
import com.example.lenovo.geek.net.WeChatCallBack;
import com.example.lenovo.geek.view.WeChatV;

public class WeChatPs implements WeChatP, WeChatCallBack {
    private WeChatM mWeChatm;
    private WeChatV mWeChatv;

    public WeChatPs(WeChatM mWeChatm, WeChatV mWeChatv) {
        this.mWeChatm = mWeChatm;
        this.mWeChatv = mWeChatv;
    }

    @Override
    public void getWechat() {
        if (mWeChatm != null) {
            mWeChatm.getWechat(this);
        }
    }

    @Override
    public void onSuccess(WeChatBean bean) {
        if (mWeChatv != null) {
            mWeChatv.onSuccess(bean);
        }
    }

    @Override
    public void onFail(String msg) {
        if (mWeChatv != null) {
            mWeChatv.onFail(msg);
        }
    }

}
