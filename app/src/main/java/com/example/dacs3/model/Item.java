package com.example.dacs3.model;

import com.example.dacs3.utils.Utils;

public class Item {
    int idsp;
    String name;
    int soluong;
    String thumb;

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getThumb() {
        return Utils.URL_BASE +"do_an_co_so_2_vku/public/uploads/products/"+thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
