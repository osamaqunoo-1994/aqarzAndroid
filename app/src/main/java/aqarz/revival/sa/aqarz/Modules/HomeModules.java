package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("operation_type_id")
    @Expose
    private String operation_type_id;


    @SerializedName("user_id")
    @Expose
    private String user_id;


    @SerializedName("request_type")
    @Expose
    private String request_type;
    @SerializedName("estate_type_id")
    @Expose
    private String estate_type_id;
    @SerializedName("area_from")
    @Expose
    private String area_from;
    @SerializedName("area_to")
    @Expose
    private String area_to;
    @SerializedName("price_from")
    @Expose
    private String price_from;
    @SerializedName("price_to")
    @Expose
    private String price_to;
    @SerializedName("room_numbers")
    @Expose
    private String room_numbers;
    @SerializedName("owner_name")
    @Expose
    private String owner_name;
    @SerializedName("display_owner_mobile")
    @Expose
    private String display_owner_mobile;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("seen_count")
    @Expose
    private String seen_count;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("operation_type_name")
    @Expose
    private String operation_type_name;
    @SerializedName("estate_type_name")
    @Expose
    private String estate_type_name;


    public Integer getId() {
        return id;
    }

    public String getStatus() {

        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getNote() {
        return note;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getArea_from() {
        return area_from;
    }

    public String getArea_to() {
        return area_to;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getDisplay_owner_mobile() {
        return display_owner_mobile;
    }

    public String getDistance() {
        return distance;
    }

    public String getEstate_type_id() {
        return estate_type_id;
    }

    public String getEstate_type_name() {
        return estate_type_name;
    }

    public String getLan() {
        return lan;
    }

    public String getLat() {
        return lat;
    }

    public String getOperation_type_id() {
        return operation_type_id;
    }

    public String getOperation_type_name() {
        return operation_type_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getPrice_from() {
        return price_from;
    }

    public String getPrice_to() {
        return price_to;
    }

    public String getRequest_type() {
        return request_type;
    }

    public String getRoom_numbers() {
        return room_numbers;
    }

    public String getSeen_count() {
        return seen_count;
    }

    public String getUpdated_at() {
        return updated_at;
    }

}
