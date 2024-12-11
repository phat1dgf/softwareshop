package com.webcuoiky.softwareshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class webAdminController {

    @RequestMapping("")
    public  String admin() {

        return "admin";
    }
    @RequestMapping("/product-list/product")
    public  String product() {

        return "admin_product";
    }
    @RequestMapping("/category")
    public  String category_list() {

        return "admin_category";
    }
    @RequestMapping("/product-list")
    public  String product_list() {

        return "admin_product_list";
    }
}