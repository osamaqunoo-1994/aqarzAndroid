package sa.aqarz.Modules;

import java.util.ArrayList;
import java.util.List;

public class ObjectFiltterOrder {


    String Typelayout = "";
    String name_city = "";
    String id_city = "";
    String id_state = "";
    String id_nib = "";


    String type_filtter = "";

    String Max_price = "";
    String Less_price = "";
    String Max_space = "";
    String Less_space = "";


    String name_price = "";
    String id_price = "";

    String name_space = "";
    String id_space = "";

    List<TypeModules> type_list = new ArrayList<>();

    public List<TypeModules> getType_list() {
        return type_list;
    }

    public void setType_list(List<TypeModules> type_list) {
        this.type_list = type_list;
    }

    public String getTypelayout() {
        return Typelayout;
    }

    public void setTypelayout(String typelayout) {
        Typelayout = typelayout;
    }

    public String getId_nib() {
        return id_nib;
    }

    public String getId_state() {
        return id_state;
    }

    public void setId_nib(String id_nib) {
        this.id_nib = id_nib;
    }

    public void setId_state(String id_state) {
        this.id_state = id_state;
    }

    public String getMax_space() {
        return Max_space;
    }

    public String getMax_price() {
        return Max_price;
    }

    public String getLess_space() {
        return Less_space;
    }

    public String getLess_price() {
        return Less_price;
    }

    public String getId_city() {
        return id_city;
    }

    public String getId_price() {
        return id_price;
    }

    public String getId_space() {
        return id_space;
    }

    public String getName_city() {
        return name_city;
    }

    public String getName_price() {
        return name_price;
    }

    public String getName_space() {
        return name_space;
    }

    public String getType_filtter() {
        return type_filtter;
    }

    public void setMax_space(String max_space) {
        Max_space = max_space;
    }

    public void setMax_price(String max_price) {
        Max_price = max_price;
    }

    public void setLess_space(String less_space) {
        Less_space = less_space;
    }

    public void setLess_price(String less_price) {
        Less_price = less_price;
    }

    public void setId_city(String id_city) {
        this.id_city = id_city;
    }

    public void setId_price(String id_price) {
        this.id_price = id_price;
    }

    public void setId_space(String id_space) {
        this.id_space = id_space;
    }

    public void setName_city(String name_city) {
        this.name_city = name_city;
    }

    public void setName_price(String name_price) {
        this.name_price = name_price;
    }

    public void setName_space(String name_space) {
        this.name_space = name_space;
    }

    public void setType_filtter(String type_filtter) {
        this.type_filtter = type_filtter;
    }
}
