package sa.aqarz.Modules;

import java.util.ArrayList;
import java.util.List;

public class Object_filtter {


    String type_aqarz_view = "";
    String type_aqarz = "";
    String less_price = "";
    String max_price = "";
    String less_space = "";
    String max_space = "";
    String estate_pay_type = "";

    int number_Lounges = 0;
    int number_room = 0;
    int number_Bathrooms = 0;
    int number_Boards_plus = 0;
    int number_Kitchens_plus = 0;
    int number_Dining_rooms = 0;
    int number_lifts = 0;
    int number_parking = 0;


    boolean north_selected = false;
    boolean south_selected = false;
    boolean east_selected = false;
    boolean west_selected = false;

    List<ComfortModules> comfortModules = new ArrayList<>();

    String date = "";

    List<TypeModules> type_list = new ArrayList<>();


    public boolean isEast_selected() {
        return east_selected;
    }

    public boolean isNorth_selected() {
        return north_selected;
    }

    public boolean isSouth_selected() {
        return south_selected;
    }

    public boolean isWest_selected() {
        return west_selected;
    }

    public List<TypeModules> getType_list() {
        return type_list;
    }

    public void setType_list(List<TypeModules> type_list) {
        this.type_list = type_list;
    }

    public String getDate() {
        return date;
    }

    public int getNumber_Bathrooms() {
        return number_Bathrooms;
    }

    public int getNumber_Boards_plus() {
        return number_Boards_plus;
    }

    public int getNumber_Dining_rooms() {
        return number_Dining_rooms;
    }

    public List<ComfortModules> getComfortModules() {
        return comfortModules;
    }

    public String getEstate_pay_type() {
        return estate_pay_type;
    }

    public void setEstate_pay_type(String estate_pay_type) {
        this.estate_pay_type = estate_pay_type;
    }

    public void setComfortModules(List<ComfortModules> comfortModules) {
        this.comfortModules = comfortModules;
    }

    public int getNumber_Kitchens_plus() {
        return number_Kitchens_plus;
    }

    public int getNumber_lifts() {
        return number_lifts;
    }

    public int getNumber_Lounges() {
        return number_Lounges;
    }

    public int getNumber_parking() {
        return number_parking;
    }

    public int getNumber_room() {
        return number_room;
    }

    public String getLess_price() {
        return less_price;
    }

    public String getLess_space() {
        return less_space;
    }

    public String getMax_price() {
        return max_price;
    }

    public String getMax_space() {
        return max_space;
    }

    public String getType_aqarz() {
        return type_aqarz;
    }

    public String getType_aqarz_view() {
        return type_aqarz_view;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setEast_selected(boolean east_selected) {
        this.east_selected = east_selected;
    }

    public void setLess_price(String less_price) {
        this.less_price = less_price;
    }

    public void setLess_space(String less_space) {
        this.less_space = less_space;
    }

    public void setMax_price(String max_price) {
        this.max_price = max_price;
    }

    public void setMax_space(String max_space) {
        this.max_space = max_space;
    }

    public void setNorth_selected(boolean north_selected) {
        this.north_selected = north_selected;
    }

    public void setNumber_Bathrooms(int number_Bathrooms) {
        this.number_Bathrooms = number_Bathrooms;
    }

    public void setNumber_Boards_plus(int number_Boards_plus) {
        this.number_Boards_plus = number_Boards_plus;
    }

    public void setNumber_Dining_rooms(int number_Dining_rooms) {
        this.number_Dining_rooms = number_Dining_rooms;
    }

    public void setNumber_Kitchens_plus(int number_Kitchens_plus) {
        this.number_Kitchens_plus = number_Kitchens_plus;
    }

    public void setNumber_lifts(int number_lifts) {
        this.number_lifts = number_lifts;
    }

    public void setNumber_Lounges(int number_Lounges) {
        this.number_Lounges = number_Lounges;
    }

    public void setNumber_parking(int number_parking) {
        this.number_parking = number_parking;
    }

    public void setNumber_room(int number_room) {
        this.number_room = number_room;
    }

    public void setSouth_selected(boolean south_selected) {
        this.south_selected = south_selected;
    }

    public void setType_aqarz(String type_aqarz) {
        this.type_aqarz = type_aqarz;
    }

    public void setType_aqarz_view(String type_aqarz_view) {
        this.type_aqarz_view = type_aqarz_view;
    }

    public void setWest_selected(boolean west_selected) {
        this.west_selected = west_selected;
    }
}
