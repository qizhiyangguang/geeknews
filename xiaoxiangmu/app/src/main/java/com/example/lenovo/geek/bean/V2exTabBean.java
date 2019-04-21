package com.example.lenovo.geek.bean;

public class V2exTabBean {
    private String link;
    private String tabs;

    public V2exTabBean(String link, String tabs) {
        this.link = link;
        this.tabs = tabs;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTabs() {
        return tabs;
    }

    public void setTabs(String tabs) {
        this.tabs = tabs;
    }

    @Override
    public String toString() {
        return "V2exTabBean{" +
                "link='" + link + '\'' +
                ", tabs='" + tabs + '\'' +
                '}';
    }
}
