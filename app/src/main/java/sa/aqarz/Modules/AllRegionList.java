package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllRegionList {


    @SerializedName("data")
    @Expose
    private List<RegionModules> data;
    @SerializedName("status")
    @Expose
    private boolean status;

    public List<RegionModules> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }
}
