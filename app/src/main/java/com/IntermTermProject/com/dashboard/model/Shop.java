package com.IntermTermProject.com.dashboard.model;

public class Shop {

    private int shopId;
    private  String shopName;
    private String ShopImage;

    public Shop() {
}

    public Shop(String shopName, String image) {
        this.shopName = shopName;
        this.ShopImage = image;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopImage() {
        return ShopImage;
    }

    public void setShopImage(String shopImage) {
        ShopImage = shopImage;
    }
}
