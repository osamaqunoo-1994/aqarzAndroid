package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class fund_favorite {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fund")
    @Expose
    private List<OrdersModules> fund;


    public Integer getId() {
        return id;
    }

    public List<OrdersModules> getEstate() {
        return fund;
    }
}
