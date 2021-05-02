package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SettingsModules {





    @SerializedName("allRequest")
    @Expose
    private String allRequest;

    @SerializedName("allRequestFund")
    @Expose
    private String allRequestFund;
    @SerializedName("marketDemands")
    @Expose
    private String marketDemands;
    @SerializedName("RequestFund")
    @Expose
    private String RequestFund;
    @SerializedName("myRequestFundOffer")
    @Expose
    private String myRequestFundOffer;
    @SerializedName("myRequestOffer")
    @Expose
    private String myRequestOffer;
    @SerializedName("requestReal")
    @Expose
    private String requestReal;
    @SerializedName("offerReal")
    @Expose
    private String offerReal;





    @SerializedName("company_info")
    @Expose
    private String company_info;
    @SerializedName("about_us")
    @Expose
    private String about_us;
    @SerializedName("privacy_policy")
    @Expose
    private String privacy_policy;
    @SerializedName("face_book")
    @Expose
    private String face_book;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("insta")
    @Expose
    private String insta;
    @SerializedName("linked")
    @Expose
    private String linked;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;


    @SerializedName("estate_types")
    @Expose
    private estate_types estate_types;


    @SerializedName("OprationType")
    @Expose
    private OprationType OprationType;


    @SerializedName("member_types")
    @Expose
    private List<service_types> member_types;


    @SerializedName("service_types")
    @Expose
    private List<service_types> service_types;

    @SerializedName("experience_types")
    @Expose
    private List<service_types> experience_types;

    @SerializedName("course_types")
    @Expose
    private List<service_types> course_types;


    public List<SettingsModules.service_types> getMember_types() {
        return member_types;
    }

    public List<SettingsModules.service_types> getService_types() {
        return service_types;
    }

    public SettingsModules.OprationType getOprationType() {
        return OprationType;
    }

    public SettingsModules.estate_types getEstate_types() {
        return estate_types;
    }

    public String getMarketDemands() {
        return marketDemands;
    }

    public String getRequestFund() {
        return RequestFund;
    }

    public List<SettingsModules.service_types> getCourse_types() {
        return course_types;
    }

    public List<SettingsModules.service_types> getExperience_types() {
        return experience_types;
    }

    public String getAllRequest() {
        return allRequest;
    }

    public String getAllRequestFund() {
        return allRequestFund;
    }

    public String getMyRequestFundOffer() {
        return myRequestFundOffer;
    }

    public String getMyRequestOffer() {
        return myRequestOffer;
    }

    public String getOfferReal() {
        return offerReal;
    }

    public String getRequestReal() {
        return requestReal;
    }

    public class estate_types {


        @SerializedName("original")
        @Expose
        private original original;

        public SettingsModules.original getOriginal() {
            return original;
        }
    }

    public class OprationType {


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

    public class service_types {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("icon")
        @Expose
        private String icon;

        public String getIcon() {
            return icon;
        }

        private boolean checked = false;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }


    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getAbout_us() {
        return about_us;
    }

    public String getCompany_info() {
        return company_info;
    }

    public String getFace_book() {
        return face_book;
    }

    public String getInsta() {
        return insta;
    }

    public String getLinked() {
        return linked;
    }

    public String getPrivacy_policy() {
        return privacy_policy;
    }
}
