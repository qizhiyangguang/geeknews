package com.example.lenovo.geek.bean;

import java.io.Serializable;

public class GoldShowBean implements Serializable {
    public String title;
    public boolean isChecked;

    public GoldShowBean(String title, boolean isChecked) {
        this.title = title;
        this.isChecked = isChecked;
    }
}
