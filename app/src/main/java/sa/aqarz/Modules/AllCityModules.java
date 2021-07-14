package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCityModules {
    @SerializedName("data")
    @Expose
    private List<City> data;
    @SerializedName("status")
    @Expose
    private boolean status;

    public List<City> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }



    public class City{

        @SerializedName("city_id")
        @Expose
        private Integer cityId;
        @SerializedName("region_id")
        @Expose
        private Integer regionId;
        @SerializedName("state_code")
        @Expose
        private String stateCode;
        @SerializedName("name_ar")
        @Expose
        private String nameAr;
        @SerializedName("name_en")
        @Expose
        private String nameEn;
        @SerializedName("center")
        @Expose
        private Center center;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

        public Integer getRegionId() {
            return regionId;
        }

        public void setRegionId(Integer regionId) {
            this.regionId = regionId;
        }

        public String getStateCode() {
            return stateCode;
        }

        public void setStateCode(String stateCode) {
            this.stateCode = stateCode;
        }

        public String getNameAr() {
            return nameAr;
        }

        public void setNameAr(String nameAr) {
            this.nameAr = nameAr;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public Center getCenter() {
            return center;
        }

        public void setCenter(Center center) {
            this.center = center;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
    }
    public class Center {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }

    }
}
