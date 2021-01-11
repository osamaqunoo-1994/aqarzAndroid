package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import sa.aqarz.Settings.Settings;

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
    @SerializedName("user_name")
    @Expose
    private String user_name;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("is_pay")
    @Expose
    private String is_pay;
    @SerializedName("onwer_name")
    @Expose
    private String onwer_name;
    @SerializedName("is_certified")
    @Expose
    private String is_certified;
    @SerializedName("is_fund_certified")
    @Expose
    private String is_fund_certified;
    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("device_type")
    @Expose
    private String device_type;
    @SerializedName("city_name")
    @Expose
    private String city_name;
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
    @SerializedName("services_id")
    @Expose
    private String services_id;
    @SerializedName("members_id")
    @Expose
    private String members_id;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("count_visit")
    @Expose
    private String count_visit;
    @SerializedName("count_offer")
    @Expose
    private String count_offer;
    @SerializedName("count_request")
    @Expose
    private String count_request;
    @SerializedName("count_agent")
    @Expose
    private String count_agent;





    @SerializedName("city_id")
    @Expose
    private String city_id;
    @SerializedName("member_name")
    @Expose
    private List<SettingsModules.service_types> member_name;
    @SerializedName("service_name")
    @Expose
    private List<SettingsModules.service_types> service_name;


    public String getServices_id() {
        return services_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public List<SettingsModules.service_types> getMember_name() {
        return member_name;
    }



    public List<SettingsModules.service_types> getService_name() {
        return service_name;
    }

    public String getMembers_id() {
        return members_id;
    }


    public String getCount_agent() {
        return count_agent;
    }

    public String getCount_offer() {
        return count_offer;
    }

    public String getCount_request() {
        return count_request;
    }

    public String getOnwer_name() {
        return onwer_name;
    }

    public String getCount_visit() {
        return count_visit;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUser_name() {
        return user_name;
    }

    public Integer getId() {
        return id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getIs_certified() {
        return is_certified;
    }

    public String getIs_fund_certified() {
        return is_fund_certified;
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

    public String getCity_name() {
        return city_name;
    }

    public String getLink() {
        return link;
    }

    public String getAddress() {
        return address;
    }

    public String getLat() {
        return lat;
    }

    public String getLan() {
        return lan;
    }
}
