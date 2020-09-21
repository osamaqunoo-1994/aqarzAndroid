package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SettingsModules {

    @SerializedName("estate_types")
    @Expose
    private estate_types estate_types;

    public SettingsModules.estate_types getEstate_types() {
        return estate_types;
    }

    public class estate_types {


        @SerializedName("original")
        @Expose
        private original original;

        public SettingsModules.original getOriginal() {
            return original;
        }
    }

    public class original {

        @SerializedName("data")
        @Expose
        private List<TypeModules> data;

        public List<TypeModules> getData() {
            return data;
        }
    }
}
