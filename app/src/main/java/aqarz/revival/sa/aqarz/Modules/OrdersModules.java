package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrdersModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("estate_type_id")
    @Expose
    private String estateTypeId;
    @SerializedName("estate_status")
    @Expose
    private String estateStatus;
    @SerializedName("area_estate_id")
    @Expose
    private Integer areaEstateId;
    @SerializedName("dir_estate_id")
    @Expose
    private Integer dirEstateId;
    @SerializedName("estate_price_id")
    @Expose
    private Integer estatePriceId;
    @SerializedName("street_view_id")
    @Expose
    private Integer streetViewId;
    @SerializedName("city_id")
    @Expose
    private Integer cityId;
    @SerializedName("neighborhood_id")
    @Expose
    private Integer neighborhoodId;
    @SerializedName("rooms_number_id")
    @Expose
    private Integer roomsNumberId;
    @SerializedName("estate_type_name")
    @Expose
    private String estateTypeName;
    @SerializedName("estate_type_icon")
    @Expose
    private String estateTypeIcon;
    @SerializedName("dir_estate")
    @Expose
    private String dirEstate;
    @SerializedName("street_view_range")
    @Expose
    private String streetViewRange;
    @SerializedName("estate_price_range")
    @Expose
    private String estatePriceRange;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("area_estate_range")
    @Expose
    private Object areaEstateRange;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("in_fav")
    @Expose
    private String in_fav;
    @SerializedName("neighborhood_name")
    @Expose
    private String neighborhoodName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEstateTypeId() {
        return estateTypeId;
    }

    public void setEstateTypeId(String estateTypeId) {
        this.estateTypeId = estateTypeId;
    }

    public String getIn_fav() {
        return in_fav;
    }

    public void setIn_fav(String in_fav) {
        this.in_fav = in_fav;
    }

    public String getEstateStatus() {
        return estateStatus;
    }


    public void setEstateStatus(String estateStatus) {
        this.estateStatus = estateStatus;
    }

    public Integer getAreaEstateId() {
        return areaEstateId;
    }

    public void setAreaEstateId(Integer areaEstateId) {
        this.areaEstateId = areaEstateId;
    }

    public Integer getDirEstateId() {
        return dirEstateId;
    }

    public void setDirEstateId(Integer dirEstateId) {
        this.dirEstateId = dirEstateId;
    }

    public Integer getEstatePriceId() {
        return estatePriceId;
    }

    public void setEstatePriceId(Integer estatePriceId) {
        this.estatePriceId = estatePriceId;
    }

    public Integer getStreetViewId() {
        return streetViewId;
    }

    public void setStreetViewId(Integer streetViewId) {
        this.streetViewId = streetViewId;
    }

    public String getEstateTypeIcon() {
        return estateTypeIcon;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getNeighborhoodId() {
        return neighborhoodId;
    }

    public void setNeighborhoodId(Integer neighborhoodId) {
        this.neighborhoodId = neighborhoodId;
    }

    public Integer getRoomsNumberId() {
        return roomsNumberId;
    }

    public void setRoomsNumberId(Integer roomsNumberId) {
        this.roomsNumberId = roomsNumberId;
    }

    public String getEstateTypeName() {
        return estateTypeName;
    }

    public void setEstateTypeName(String estateTypeName) {
        this.estateTypeName = estateTypeName;
    }

    public String getDirEstate() {
        return dirEstate;
    }

    public void setDirEstate(String dirEstate) {
        this.dirEstate = dirEstate;
    }

    public String getStreetViewRange() {
        return streetViewRange;
    }

    public void setStreetViewRange(String streetViewRange) {
        this.streetViewRange = streetViewRange;
    }

    public String getEstatePriceRange() {
        return estatePriceRange;
    }

    public void setEstatePriceRange(String estatePriceRange) {
        this.estatePriceRange = estatePriceRange;
    }

    public Object getAreaEstateRange() {
        return areaEstateRange;
    }

    public void setAreaEstateRange(Object areaEstateRange) {
        this.areaEstateRange = areaEstateRange;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(String neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

}
