package com.example.dummyjson;

public class Product {
    String title, desc, imageUrl, price, rating;

    public Product(String title, String desc, String price, String rating, String imageUrl) {
        this.title = title;
        this.desc = desc;
        this.price = price;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }
}
