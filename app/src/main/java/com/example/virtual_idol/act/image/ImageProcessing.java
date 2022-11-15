package com.example.virtual_idol.act.image;

public class ImageProcessing {
    private static ImageProcessing instance;

    public static ImageProcessing getInstance() {
        if (instance == null)
            instance = new ImageProcessing();
        return instance;
    }


}
