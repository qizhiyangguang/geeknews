package com.example.lenovo.geek.widget;

public interface TouchCallBack {
    //数据交换
    void onItemMove(int formPosition, int toPosition);

    //删除条目
    void onItemDelete(int position);
}
