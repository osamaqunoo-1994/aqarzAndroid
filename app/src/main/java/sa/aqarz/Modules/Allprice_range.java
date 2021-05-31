package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Allprice_range {


    @SerializedName("data")
    @Expose
    private List<price_range> data;
    @SerializedName("status")
    @Expose
    private boolean status;


    public List<price_range> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }


    public class data {
        @SerializedName("data")
        @Expose
        private List<price_range> data;


        public List<price_range> getData() {
            return data;
        }
    }


}
