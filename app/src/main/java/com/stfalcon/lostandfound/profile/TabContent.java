package com.stfalcon.lostandfound.profile;


/**
 * Created by shpak on 29.03.15.
 */
public class TabContent {
    private String name;
    private String time;
    private String text;
    private int image;

    TabContent(String _name,String _time,String _text,int _image){
        setName(_name);
        setTime(_time);
        setText(_text);
        setImage(_image);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
