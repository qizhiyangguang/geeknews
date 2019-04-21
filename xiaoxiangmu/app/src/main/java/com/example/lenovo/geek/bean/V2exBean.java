package com.example.lenovo.geek.bean;

public class V2exBean {
    private String img;
    private String author;
    private String href;
    private String number;
    private String topic;
    private String titles;

    public V2exBean(String img, String author, String href, String number, String topic, String titles) {
        this.img = img;
        this.author = author;
        this.href = href;
        this.number = number;
        this.topic = topic;
        this.titles = titles;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    @Override
    public String toString() {
        return "V2exBean{" +
                "img='" + img + '\'' +
                ", author='" + author + '\'' +
                ", href='" + href + '\'' +
                ", number='" + number + '\'' +
                ", topic='" + topic + '\'' +
                ", titles='" + titles + '\'' +
                '}';
    }
}
