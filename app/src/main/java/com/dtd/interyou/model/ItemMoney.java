package com.dtd.interyou.model;

import android.graphics.drawable.Drawable;

/**
 * Created by Egor on 01.06.2015.
 */
public class ItemMoney {

    private Drawable ImageId;
    private String Title;
    private String Sum;

    public ItemMoney(Drawable imageId, String title, String sum) {
        ImageId = imageId;
        Title = title;
        Sum = sum;
    }

    public ItemMoney() {
    }

    public Drawable getImageId() {
        return ImageId;
    }

    public void setImageId(Drawable imageId) {
        ImageId = imageId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSum() {
        return Sum;
    }

    public void setSum(String sum) {
        Sum = sum;
    }
}
