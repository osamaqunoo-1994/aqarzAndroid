package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllCity_WithNib {
    @SerializedName("data")
    @Expose
    private List<data> data;

    @SerializedName("status")
    @Expose
    private boolean status;


    public List<AllCity_WithNib.data> getData() {
        return data;
    }

    public boolean isStatus() {
        return status;
    }

    public class data {


        @SerializedName("city_id")
        @Expose
        private Integer city_id;
        @SerializedName("city_name")
        @Expose
        private String city_name;
        @SerializedName("region_id")
        @Expose
        private Integer region_id;
        @SerializedName("neighborhoods")
        @Expose
        private List<neighborhoods> neighborhoods;

        public Integer getCity_id() {
            return city_id;
        }

        public Integer getRegion_id() {
            return region_id;
        }

        public List<AllCity_WithNib.neighborhoods> getNeighborhoods() {
            return neighborhoods;
        }

        public String getCity_name() {
            return city_name;
        }
    }

    public class neighborhoods {

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
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("center")
        @Expose
        private String center;

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public Integer getCityId() {
            return cityId;
        }

        public String getCenter() {
            return center;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }






}
