package com.example.turaapp;

public class DestinationItem {

    private String name;
    private String info;
    private String km;
    private final int imageResource;

    public DestinationItem(int imageResource, String km, String info, String name) {
        this.imageResource = imageResource;
        this.km = km;
        this.info = info;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getKm() {
        return km;
    }

    public int getImageResource() {
        return imageResource;
    }



}
