package com.example.homework422;

public class ItemData {
    private String title;
    private String subtitle;
    private int img_src;

    public ItemData(String title, String subtitle, int img_src) {
        this.title = title;
        this.subtitle = subtitle;
        this.img_src = img_src;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getImg_src() {
        return img_src;
    }
}
