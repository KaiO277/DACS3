package com.example.dacs3.model;

import com.example.dacs3.utils.Utils;

import java.io.Serializable;

public class SapPhamMoi implements Serializable {

    int id;
    String name;
    String description;
    String thumb;
    String price;
    String menu_id;


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

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumb() {
        return Utils.URL_BASE +"do_an_co_so_2_vku/public/uploads/products/"+thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
}
