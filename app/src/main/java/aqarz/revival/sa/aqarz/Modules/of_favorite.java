package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class of_favorite {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("estate")
    @Expose
    private List<HomeModules_aqares> estate;


    public Integer getId() {
        return id;
    }

    public List<HomeModules_aqares> getEstate() {
        return estate;
    }
}
