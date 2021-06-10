package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllNeigbers {


    @SerializedName("data")
    @Expose
    private List<Neighborhood> data;
    @SerializedName("status")
    @Expose
    private boolean status;

    public List<Neighborhood> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }
}
