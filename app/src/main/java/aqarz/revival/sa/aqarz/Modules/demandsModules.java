package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class demandsModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("operation_type_id")
    @Expose
    private Integer operationTypeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("request_type")
    @Expose
    private String requestType;
    @SerializedName("street_view_range")
    @Expose
    private String street_view_range;
    @SerializedName("estate_price_range")
    @Expose
    private String estate_price_range;
    @SerializedName("estate_type_id")
    @Expose
    private Integer estateTypeId;
    @SerializedName("area_from")
    @Expose
    private String areaFrom;
    @SerializedName("area_to")
    @Expose
    private String areaTo;
    @SerializedName("in_fav")
    @Expose
    private String in_fav;
    @SerializedName("price_from")
    @Expose
    private String priceFrom;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("price_to")
    @Expose
    private String priceTo;
    @SerializedName("room_numbers")
    @Expose
    private Integer roomNumbers;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("owner_mobile")
    @Expose
    private String ownerMobile;
    @SerializedName("display_owner_mobile")
    @Expose
    private String displayOwnerMobile;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("seen_count")
    @Expose
    private Integer seenCount;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("operation_type_name")
    @Expose
    private String operationTypeName;
    @SerializedName("estate_type_name")
    @Expose
    private String estateTypeName;
    @SerializedName("city_name")
    @Expose
    private String city_name;
    @SerializedName("neighborhood_name")
    @Expose
    private String neighborhood_name;
    @SerializedName("operation_type")
    @Expose
    private TypeModules operation_type;
    @SerializedName("estate_type")
    @Expose
    private TypeModules estate_type;

    public TypeModules getEstate_type() {
        return estate_type;
    }

    public TypeModules getOperation_type() {
        return operation_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Integer operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public String getIn_fav() {
        return in_fav;
    }

    public void setIn_fav(String in_fav) {
        this.in_fav = in_fav;
    }

    public String getNeighborhood_name() {
        return neighborhood_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getEstateTypeId() {
        return estateTypeId;
    }

    public void setEstateTypeId(Integer estateTypeId) {
        this.estateTypeId = estateTypeId;
    }

    public String getAreaFrom() {
        return areaFrom;
    }

    public void setAreaFrom(String areaFrom) {
        this.areaFrom = areaFrom;
    }

    public String getAreaTo() {
        return areaTo;
    }

    public void setAreaTo(String areaTo) {
        this.areaTo = areaTo;
    }

    public String getEstate_price_range() {
        return estate_price_range;
    }

    public String getStreet_view_range() {
        return street_view_range;
    }

    public String getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(String priceFrom) {
        this.priceFrom = priceFrom;
    }

    public String getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(String priceTo) {
        this.priceTo = priceTo;
    }

    public Integer getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(Integer roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getDisplayOwnerMobile() {
        return displayOwnerMobile;
    }

    public void setDisplayOwnerMobile(String displayOwnerMobile) {
        this.displayOwnerMobile = displayOwnerMobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSeenCount() {
        return seenCount;
    }

    public void setSeenCount(Integer seenCount) {
        this.seenCount = seenCount;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }

    public String getEstateTypeName() {
        return estateTypeName;
    }

    public void setEstateTypeName(String estateTypeName) {
        this.estateTypeName = estateTypeName;
    }
}
