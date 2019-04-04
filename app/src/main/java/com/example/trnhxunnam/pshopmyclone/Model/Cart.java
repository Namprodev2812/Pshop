package com.example.trnhxunnam.pshopmyclone.Model;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

public class Cart {

    private static Cart instance;
    private HashMap<Product, Integer> cart = new HashMap<>();
    private Cart() {
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addProduct(Product product, Integer soluong) {
        cart.put(product, soluong);
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void clearCart() {
        cart.clear();
    }
}
