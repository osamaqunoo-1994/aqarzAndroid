package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class select_typeModules {

    Integer id;
    String name;
    boolean selected = false;

    public select_typeModules(Integer id,
                              String name) {
        this.id = id;
        this.name = name;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getName() {
        return name;
    }

    public boolean getSelected() {
        return selected;
    }

    public Integer getId() {
        return id;
    }
}
