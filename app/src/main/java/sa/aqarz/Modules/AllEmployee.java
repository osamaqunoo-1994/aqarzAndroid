package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllEmployee {



    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("emp_name")
    @Expose
    private String emp_name;
    @SerializedName("emp_mobile")
    @Expose
    private String emp_mobile;
    @SerializedName("country_code")
    @Expose
    private String country_code;
    @SerializedName("user")
    @Expose
    private  User user;




    public User getUser() {
        return user;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getCountry_code() {
        return country_code;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }
}
