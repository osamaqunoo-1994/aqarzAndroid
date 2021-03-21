package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCityList {


    @SerializedName("data")
    @Expose
    private List<CityLocation> data;
    @SerializedName("status")
    @Expose
    private boolean status;

    public List<CityLocation> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }
}
