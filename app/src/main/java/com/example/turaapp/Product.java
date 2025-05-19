
package com.example.turaapp;


public class Product {
    public String name;

    public String description;

    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;

        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
