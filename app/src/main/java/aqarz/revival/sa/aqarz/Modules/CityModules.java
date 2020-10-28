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
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name_en")
    @Expose
    private String name_en;
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
    @SerializedName("serial_city")
    @Expose
    private String serial_city;
    @SerializedName("country_id")
    @Expose
    private String country_id;



    boolean selected=false;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSerial_city() {
        return serial_city;
    }

    public String getCountry_id() {
        return country_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getName_ar() {
        return name_ar;
    }

    public String getName_en() {
        return name_en;
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
