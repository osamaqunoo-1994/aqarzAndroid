package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityLocation {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String  name;

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("long")
    @Expose
    private String lang;




    public CityLocation(Integer id,String  name,String lat,String lang){

        this.id=id;
        this.name=name;
        this.lat=lat;
        this.lang=lang;

    }




    public Integer getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    public String getLat() {
        return lat;
    }

    public String getName() {
        return name;
    }
}
