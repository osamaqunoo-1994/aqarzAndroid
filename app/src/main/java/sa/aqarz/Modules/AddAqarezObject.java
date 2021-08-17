package sa.aqarz.Modules;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddAqarezObject {


    String estate_type_id = "";
    String state_id = "";
    String city_id = "";
    String neighborhood_id = "";
    String street_name = "";
    String lat = "";
    String lan = "";
    String address = "";
    String advertiser_side = "";
    String operation_type_id = "";
    String estate_use_type = "";
    String advertiser_character = "";
    String is_disputes = "0";
    String is_mortgage = "0";
    String is_obligations = "0";
    String is_saudi_building_code = "0";
    List<SelectImageModules> selectIamgeList = new ArrayList<>();
    List<ComfortModules> comfortModules = new ArrayList<>();

    File video;



    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public void setNeighborhood_id(String neighborhood_id) {
        this.neighborhood_id = neighborhood_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getNeighborhood_id() {
        return neighborhood_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public String getLan() {
        return lan;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setOperation_type_id(String operation_type_id) {
        this.operation_type_id = operation_type_id;
    }

    public String getOperation_type_id() {
        return operation_type_id;
    }

    public String getEstate_type_id() {
        return estate_type_id;
    }

    public void setEstate_type_id(String estate_type_id) {
        this.estate_type_id = estate_type_id;
    }

    public String getEstate_use_type() {

        return estate_use_type;
    }

    public void setEstate_use_type(String estate_use_type) {
        this.estate_use_type = estate_use_type;
    }

    public List<SelectImageModules> getSelectIamgeList() {
        return selectIamgeList;
    }

    public void setSelectIamgeList(List<SelectImageModules> selectIamgeList) {
        this.selectIamgeList = selectIamgeList;
    }

    public File getVideo() {
        return video;
    }

    public String getIs_disputes() {
        return is_disputes;
    }

    public void setIs_disputes(String is_disputes) {
        this.is_disputes = is_disputes;
    }

    public void setVideo(File video) {
        this.video = video;
    }

    public String getIs_mortgage() {
        return is_mortgage;
    }

    public void setIs_mortgage(String is_mortgage) {
        this.is_mortgage = is_mortgage;
    }

    public String getIs_obligations() {
        return is_obligations;
    }

    public void setIs_obligations(String is_obligations) {
        this.is_obligations = is_obligations;
    }

    public String getIs_saudi_building_code() {
        return is_saudi_building_code;
    }

    public void setIs_saudi_building_code(String is_saudi_building_code) {
        this.is_saudi_building_code = is_saudi_building_code;
    }

    public List<ComfortModules> getComfortModules() {
        return comfortModules;
    }

    public void setComfortModules(List<ComfortModules> comfortModules) {
        this.comfortModules = comfortModules;
    }

    public String getAdvertiser_side() {
        return advertiser_side;
    }

    public void setAdvertiser_side(String advertiser_side) {
        this.advertiser_side = advertiser_side;
    }

    public String getAdvertiser_character() {
        return advertiser_character;
    }

    public void setAdvertiser_character(String advertiser_character) {
        this.advertiser_character = advertiser_character;
    }
}
