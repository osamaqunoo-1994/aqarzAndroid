package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locations {
    public class Address {

        @SerializedName("shop")
        @Expose
        private String shop;
        @SerializedName("tourism")
        @Expose
        private String tourism;
        @SerializedName("road")
        @Expose
        private String road;
        @SerializedName("suburb")
        @Expose
        private String suburb;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state_district")
        @Expose
        private String stateDistrict;
        @SerializedName("region")
        @Expose
        private String region;
        @SerializedName("postcode")
        @Expose
        private String postcode;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("country_code")
        @Expose
        private String countryCode;

        public String getShop() {
            return shop;
        }

        public void setShop(String shop) {
            this.shop = shop;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }

        public String getTourism() {
            return tourism;
        }

        public String getSuburb() {
            return suburb;
        }

        public void setSuburb(String suburb) {
            this.suburb = suburb;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStateDistrict() {
            return stateDistrict;
        }

        public void setStateDistrict(String stateDistrict) {
            this.stateDistrict = stateDistrict;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

    }

        @SerializedName("place_id")
        @Expose
        private Integer placeId;
        @SerializedName("licence")
        @Expose
        private String licence;
        @SerializedName("osm_type")
        @Expose
        private String osmType;
        @SerializedName("osm_id")
        @Expose
        private Long osmId;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("lon")
        @Expose
        private String lon;
        @SerializedName("display_name")
        @Expose
        private String displayName;
        @SerializedName("address")
        @Expose
        private Address address;
        @SerializedName("boundingbox")
        @Expose
        private List<String> boundingbox = null;

        public Integer getPlaceId() {
            return placeId;
        }

        public void setPlaceId(Integer placeId) {
            this.placeId = placeId;
        }

        public String getLicence() {
            return licence;
        }

        public void setLicence(String licence) {
            this.licence = licence;
        }

        public String getOsmType() {
            return osmType;
        }

        public void setOsmType(String osmType) {
            this.osmType = osmType;
        }

        public Long getOsmId() {
            return osmId;
        }

        public void setOsmId(Long osmId) {
            this.osmId = osmId;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public List<String> getBoundingbox() {
            return boundingbox;
        }

        public void setBoundingbox(List<String> boundingbox) {
            this.boundingbox = boundingbox;
        }

    }

