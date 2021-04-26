package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllEstate_map {


    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private List<HomeModules_aqares> data;

    public List<HomeModules_aqares> getData() {
        return data;
    }


    public boolean isStatus() {
        return status;
    }


    public class data {
        @SerializedName("data")
        @Expose
        private List<HomeModules_aqares> data;


        public List<HomeModules_aqares> getData() {
            return data;
        }
    }


}
