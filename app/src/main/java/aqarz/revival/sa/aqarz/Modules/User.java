package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("email_verified_at")
    @Expose
    private String email_verified_at;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("is_pay")
    @Expose
    private String is_pay;
    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("device_type")
    @Expose
    private String device_type;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("api_token")
    @Expose
    private String api_token;
    @SerializedName("country_code")
    @Expose
    private String country_code;
    @SerializedName("confirmation_code")
    @Expose
    private String confirmation_code;


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public Integer getId() {
        return id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getLogo() {
        return logo;
    }

    public String getType() {
        return type;
    }

    public String getApi_token() {
        return api_token;
    }

    public String getConfirmation_code() {
        return confirmation_code;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getDevice_token() {
        return device_token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public String getIs_pay() {
        return is_pay;
    }
}
