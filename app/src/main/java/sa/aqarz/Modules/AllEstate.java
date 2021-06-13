package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllEstate {


    @SerializedName("data")
    @Expose
    private data data;

    @SerializedName("status")
    @Expose
    private boolean status;


    public AllEstate.data getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }



    public class data {
        @SerializedName("data")
        @Expose
        private List<HomeModules_aqares> data;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("to")
        @Expose
        private String to;
        public String getTo() {
            return to;
        }

        public String getTotal() {
            return total;
        }
        public List<HomeModules_aqares> getData() {
            return data;
        }
    }


}
