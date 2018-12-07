package com.example.administrator.zqzy.base.bean;

public class DropDownPhotoItem {
    private String code;
    private String value;
    private String img;
    public DropDownPhotoItem(String code, String value,String img) {
        super();
        this.code = code;
        this.value = value;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
