package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class mod_favorite {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("request")
    @Expose
    private List<demandsModules> request;



public Integer getId() {
    return id;
}

    public List<demandsModules> getRequest() {
        return request;
    }
}
