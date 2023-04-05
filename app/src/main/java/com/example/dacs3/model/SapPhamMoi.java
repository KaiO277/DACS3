package com.example.dacs3.model;

public class SapPhamMoi {
    int id;
    String name;
    String description;
    String thumb;
    String price;
    public static String URL_BASE="http://10.23.11.93/";

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
        return URL_BASE+"do_an_co_so_2_vku/public/uploads/products/"+thumb;
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
}
