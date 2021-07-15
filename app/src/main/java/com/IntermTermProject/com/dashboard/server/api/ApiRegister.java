package com.IntermTermProject.com.dashboard.server.api;

import com.IntermTermProject.com.dashboard.server.service.CategoryService;
import com.IntermTermProject.com.dashboard.server.service.ImageUploads;
import com.IntermTermProject.com.dashboard.server.service.MyCartService;
import com.IntermTermProject.com.dashboard.server.service.PreOrderService;
import com.IntermTermProject.com.dashboard.server.service.ProductService;
import com.IntermTermProject.com.dashboard.server.service.SliderService;
import com.IntermTermProject.com.dashboard.server.service.UserProfileService;

public class ApiRegister {
    public static final String URL_001 = "http://192.168.0.104:5050/myshop-1.0.0/";
    private static RetrofitInstance Client;


    public static CategoryService getCategoryService() {
        return Client.getClient(URL_001).create(CategoryService.class);
    }

    public static ProductService getProductService() {
        return Client.getClient(URL_001).create(ProductService.class);
    }

    public static MyCartService getMyCartService() {
        return Client.getClient(URL_001).create(MyCartService.class);
    }

    public static PreOrderService getPreOrderService() {
        return Client.getClient(URL_001).create(PreOrderService.class);
    }

    public static UserProfileService getUserProfileService() {
        return Client.getClient(URL_001).create(UserProfileService.class);
    }

    public static SliderService getSliderService() {
        return Client.getClient(URL_001).create(SliderService.class);
    }

    public static ImageUploads uploadImage() {
        return Client.getClient(URL_001).create(ImageUploads.class);
    }
}
