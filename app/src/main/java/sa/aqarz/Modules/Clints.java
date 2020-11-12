package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clints {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("client_mobile")
    @Expose
    private String clientMobile;
    @SerializedName("request_type")
    @Expose
    private Integer requestType;
    @SerializedName("ads_number")
    @Expose
    private String adsNumber;
    @SerializedName("priority")
    @Expose
    private String priority;
    @SerializedName("remember")
    @Expose
    private String remember;
    @SerializedName("remember_date_time")
    @Expose
    private String rememberDateTime;
    @SerializedName("source_type")
    @Expose
    private String sourceType;
    @SerializedName("estate_type_name")
    @Expose
    private String estateTypeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getAdsNumber() {
        return adsNumber;
    }

    public void setAdsNumber(String adsNumber) {
        this.adsNumber = adsNumber;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRemember() {
        return remember;
    }

    public void setRemember(String remember) {
        this.remember = remember;
    }

    public String getRememberDateTime() {
        return rememberDateTime;
    }

    public void setRememberDateTime(String rememberDateTime) {
        this.rememberDateTime = rememberDateTime;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getEstateTypeName() {
        return estateTypeName;
    }

    public void setEstateTypeName(String estateTypeName) {
        this.estateTypeName = estateTypeName;
    }
}
