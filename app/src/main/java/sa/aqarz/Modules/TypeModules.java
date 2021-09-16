package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypeModules {


    @SerializedName("id")
    @Expose
    private final Integer id;
    @SerializedName("name")
    @Expose
    private final String name;
    @SerializedName("icon")
    @Expose
    private final String icon;

    public TypeModules(int id, String name, String icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }


    public String getIcon() {
        return icon;
    }

    boolean isselected = false;

    public boolean isIsselected() {
        return isselected;
    }

    public void setIsselected(boolean isselected) {
        this.isselected = isselected;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
