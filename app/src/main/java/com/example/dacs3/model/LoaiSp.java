package com.example.dacs3.model;

import com.example.dacs3.utils.Utils;

public class LoaiSp {
    private int id;
    private String name;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Utils.URL_BASE+ "do_an_co_so_2_vku/public/images/menuhome/"+image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
