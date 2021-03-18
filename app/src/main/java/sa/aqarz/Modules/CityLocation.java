package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityLocation {

    @SerializedName("id")
    @Expose
    private final Integer id;

    @SerializedName("name")
    @Expose
    private final String  name;

    @SerializedName("lat")
    @Expose
    private final String lat;

    @SerializedName("long")
    @Expose
    private final String lang;

    @SerializedName("lan")
    @Expose
    private String lan;

    @SerializedName("is_selected")
    @Expose
    private String is_selected;

    @SerializedName("is_fund_selected")
    @Expose
    private String is_fund_selected;

    @SerializedName("count_neighborhood")
    @Expose
    private String count_neighborhood;

    @SerializedName("city_state")
    @Expose
    private String city_state;
    @SerializedName("count_fund_request")
    @Expose
    private String count_fund_request;

    @SerializedName("count_app_request")
    @Expose
    private String count_app_request;





    public CityLocation(Integer id,String  name,String lat,String lang){

        this.id=id;
        this.name=name;
        this.lat=lat;
        this.lang=lang;

    }




    public Integer getId() {
        return id;
    }

    public String getLan() {
        return lan;
    }

    public String getLang() {
        return lang;
    }

    public String getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }

    public String getCity_state() {
        return city_state;
    }

    public String getCount_app_request() {
        return count_app_request;
    }

    public String getCount_fund_request() {
        return count_fund_request;
    }

    public String getCount_neighborhood() {
        return count_neighborhood;
    }

    public String getIs_fund_selected() {
        return is_fund_selected;
    }

    public String getIs_selected() {
        return is_selected;
    }

}
