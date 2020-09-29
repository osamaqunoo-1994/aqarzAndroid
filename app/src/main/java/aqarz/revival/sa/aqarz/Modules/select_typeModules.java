package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class select_typeModules {

    Integer id;
    String name;

    public select_typeModules(Integer id,
                              String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
