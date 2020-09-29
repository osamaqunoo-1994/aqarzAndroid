package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComfortModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    boolean is_selected = false;


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public boolean get_is_selected() {
        return is_selected;
    }


    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }
}
