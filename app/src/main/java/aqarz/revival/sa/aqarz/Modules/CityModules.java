package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("state_id")
    @Expose
    private String state_id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("country_id")
    @Expose
    private String country_id;

    public String getCountry_id() {
        return country_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getStatus() {
        return status;
    }

    public String getState_id() {
        return state_id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
}
