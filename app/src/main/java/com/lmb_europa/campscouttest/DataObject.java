package com.lmb_europa.campscout;

/**
 * Created by AleksandraPC on 18-Oct-17.
 */

public class DataObject {

    private int image, image1, image2;
    private String mText1;
    private String mText2;

    DataObject (String text1, String text2, int Image, int Image1, int Image2){
        mText1 = text1;
        mText2 = text2;
        image = Image;
        image1 = Image1;
        image2 = Image2;
    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int Image) {
        this.image = Image;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int Image1) {
        this.image1 = Image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int Image2) {
        this.image2 = Image2;
    }
}
