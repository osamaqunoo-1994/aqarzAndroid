package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegionModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("capital_city_id")
    @Expose
    private String capital_city_id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name_ar")
    @Expose
    private String name_ar;
    @SerializedName("name_en")
    @Expose
    private String name_en;
    @SerializedName("center")
    @Expose
    private center center;
    @SerializedName("boundaries")
    @Expose
    private centerx boundaries;
    @SerializedName("population")
    @Expose
    private String population;
    @SerializedName("offers")
    @Expose
    private String offers;
    @SerializedName("providers")
    @Expose
    private String providers;
    @SerializedName("name")
    @Expose
    private String name;

    public String getCode() {
        return code;
    }

    public centerx getBoundaries() {
        return boundaries;
    }

    public String getCapital_city_id() {
        return capital_city_id;
    }

    public center getCenter() {
        return center;
    }

    public String getName_ar() {
        return name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public String getOffers() {

        return offers;
    }

    public String getPopulation() {
        return population;
    }

    public String getProviders() {
        return providers;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }


    public class centerx {

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
    public class center {

        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("coordinates")
        @Expose
        private List<Double> coordinates;

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public String getType() {
            return type;
        }


    }
}
