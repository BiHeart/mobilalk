package com.example.turaapp;

import java.util.List;

public interface ProductCallback {
    void onProductsReady(List<Product> products);
}
