package aqarz.revival.sa.aqarz.Modules;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectImageModules {


    int id;

    Bitmap selectedImage;


    public SelectImageModules(int id,
                              Bitmap selectedImage) {
        this.id = id;
        this.selectedImage = selectedImage;

    }

    public int getId() {
        return id;
    }

    public Bitmap getSelectedImage() {
        return selectedImage;
    }

}
