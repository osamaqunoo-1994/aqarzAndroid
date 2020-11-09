package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModules {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("operation_type_id")
    @Expose
    private String operation_type_id;


    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("in_fav")
    @Expose
    private String in_fav;
    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("request_type")
    @Expose
    private String request_type;
    @SerializedName("estate_type_id")
    @Expose
    private String estate_type_id;
    @SerializedName("area_from")
    @Expose
    private String area_from;
    @SerializedName("area_to")
    @Expose
    private String area_to;
    @SerializedName("price_from")
    @Expose
    private String price_from;
    @SerializedName("price_to")
    @Expose
    private String price_to;
    @SerializedName("room_numbers")
    @Expose
    private String room_numbers;
    @SerializedName("owner_name")
    @Expose
    private String owner_name;
    @SerializedName("display_owner_mobile")
    @Expose
    private String display_owner_mobile;
    @SerializedName("owner_mobile")
    @Expose
    private String owner_mobile;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("seen_count")
    @Expose
    private String seen_count;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("deleted_at")
    @Expose
    private String deleted_at;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("city_name")
    @Expose
    private String city_name;
    @SerializedName("neighborhood_name")
    @Expose
    private String neighborhood_name;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("operation_type_name")
    @Expose
    private String operation_type_name;
    @SerializedName("estate_type_name")
    @Expose
    private String estate_type_name;

    @SerializedName("estate_type")
    @Expose
    private HomeModules_aqares.estate_type estate_type;
    @SerializedName("estate_file")
    @Expose
    private List<HomeModules_aqares.estate_file> estate_file;

    public List<HomeModules_aqares.estate_file> getEstate_file() {
        return estate_file;
    }

    public HomeModules_aqares.estate_type getEstate_type() {
        return estate_type;
    }

    public Integer getId() {
        return id;
    }

    public String getStatus() {

        return status;
    }

    public String getOwner_mobile() {
        return owner_mobile;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getNote() {
        return note;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getArea_from() {
        return area_from;
    }

    public String getArea_to() {
        return area_to;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public String getDisplay_owner_mobile() {
        return display_owner_mobile;
    }

    public String getDistance() {
        return distance;
    }

    public String getEstate_type_id() {
        return estate_type_id;
    }

    public String getEstate_type_name() {
        return estate_type_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getNeighborhood_name() {
        return neighborhood_name;
    }

    public String getLan() {
        return lan;
    }

    public String getLat() {
        return lat;
    }

    public String getOperation_type_id() {
        return operation_type_id;
    }

    public void setIn_fav(String in_fav) {
        this.in_fav = in_fav;
    }

    public String getOperation_type_name() {
        return operation_type_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public String getPrice_from() {
        return price_from;
    }

    public String getPrice_to() {
        return price_to;
    }

    public String getRequest_type() {
        return request_type;
    }

    public String getAddress() {
        return address;
    }

    public String getIn_fav() {
        return in_fav;
    }

    public String getRoom_numbers() {
        return room_numbers;
    }

    public String getSeen_count() {
        return seen_count;
    }

    public String getUpdated_at() {
        return updated_at;
    }
    public class estate_type {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name_ar")
        @Expose
        private String nameAr;
        @SerializedName("name_en")
        @Expose
        private String nameEn;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("deleted_at")
        @Expose
        private Object deletedAt;
        @SerializedName("created_at")
        @Expose
        private Object createdAt;
        @SerializedName("updated_at")
        @Expose
        private Object updatedAt;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("icon")
        @Expose
        private String icon;

        public String getIcon() {
            return icon;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNameAr() {
            return nameAr;
        }

        public void setNameAr(String nameAr) {
            this.nameAr = nameAr;
        }

        public String getNameEn() {
            return nameEn;
        }

        public void setNameEn(String nameEn) {
            this.nameEn = nameEn;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getDeletedAt() {
            return deletedAt;
        }

        public void setDeletedAt(Object deletedAt) {
            this.deletedAt = deletedAt;
        }

        public Object getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Object createdAt) {
            this.createdAt = createdAt;
        }

        public Object getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(Object updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public class estate_file {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("estate_id")
        @Expose
        private String estate_id;
        @SerializedName("file")
        @Expose
        private String file;
        @SerializedName("icon")
        @Expose
        private String icon;

        public String getIcon() {
            return icon;
        }

        public Integer getId() {
            return id;
        }

        public String getFile() {
            return file;
        }

        public String getEstate_id() {
            return estate_id;
        }
    }
}
