package sa.aqarz.Modules;

public class FiltterModules {


    String type_oprtion = "";
    String order_by_price = "";
    String select_city = "";
    String type_type = "";


    public void setOrder_by_price(String order_by_price) {
        this.order_by_price = order_by_price;
    }

    public void setSelect_city(String select_city) {
        this.select_city = select_city;
    }

    public void setType_oprtion(String type_oprtion) {
        this.type_oprtion = type_oprtion;
    }

    public void setType_type(String type_type) {
        this.type_type = type_type;
    }

    public String getOrder_by_price() {
        return order_by_price;
    }

    public String getSelect_city() {
        return select_city;
    }

    public String getType_oprtion() {
        return type_oprtion;
    }

    public String getType_type() {
        return type_type;
    }

}
