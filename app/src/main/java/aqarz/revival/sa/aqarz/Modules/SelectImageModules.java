package aqarz.revival.sa.aqarz.Modules;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectImageModules {


    String id;

    Bitmap selectedImage;


    public SelectImageModules(String id,
                              Bitmap selectedImage) {
        this.id = id;
        this.selectedImage = selectedImage;

    }

    public String getId() {
        return id;
    }

    public Bitmap getSelectedImage() {
        return selectedImage;
    }

}
