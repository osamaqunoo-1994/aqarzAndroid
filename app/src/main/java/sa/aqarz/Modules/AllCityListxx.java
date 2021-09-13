package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCityListxx {


    @SerializedName("data")
    @Expose
    private List<AllCityModules.City> data;


    public List<AllCityModules.City> getData() {
        return data;
    }

}
