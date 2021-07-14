package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlLNebModules {
    @SerializedName("data")
    @Expose
    private List<neb> data;
    @SerializedName("status")
    @Expose
    private boolean status;

    public List<neb> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }



    public class neb {

        @SerializedName("district_id")
        @Expose
        private String districtId;
        @SerializedName("city_id")
        @Expose
        private Integer cityId;
        @SerializedName("region_id")
        @Expose
        private Integer regionId;
        @SerializedName("name_ar")
        @Expose
        private String nameAr;
        @SerializedName("name_en")
        @Expose
        private String nameEn;
        @SerializedName("boundaries")
        @Expose
        private Boundaries boundaries;
        @SerializedName("in_my_interset")
        @Expose
        private String inMyInterset;

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

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

        public Boundaries getBoundaries() {
            return boundaries;
        }

        public void setBoundaries(Boundaries boundaries) {
            this.boundaries = boundaries;
        }

        public String getInMyInterset() {
            return inMyInterset;
        }

        public void setInMyInterset(String inMyInterset) {
            this.inMyInterset = inMyInterset;
        }

    }
    public class Boundaries {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<List<List<Double>>> coordinates = null;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<List<List<Double>>> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<List<List<Double>>> coordinates) {
            this.coordinates = coordinates;
        }

    }
}
