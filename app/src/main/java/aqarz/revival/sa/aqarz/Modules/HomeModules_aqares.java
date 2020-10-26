package aqarz.revival.sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeModules_aqares {


    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("operation_type_id")
    @Expose
    private Integer operationTypeId;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("estate_type_id")
    @Expose
    private Integer estateTypeId;
    @SerializedName("instrument_number")
    @Expose
    private String instrumentNumber;


    @SerializedName("pace_number")
    @Expose
    private String paceNumber;
    @SerializedName("planned_number")
    @Expose
    private String plannedNumber;
    @SerializedName("total_area")
    @Expose
    private String totalArea;
    @SerializedName("first_image")
    @Expose
    private String first_image;
    @SerializedName("estate_age")
    @Expose
    private Integer estateAge;
    @SerializedName("floor_number")
    @Expose
    private Integer floorNumber;
    @SerializedName("street_view")
    @Expose
    private String streetView;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("meter_price")
    @Expose
    private String meterPrice;
    @SerializedName("owner_name")
    @Expose
    private String ownerName;
    @SerializedName("city_name")
    @Expose
    private String city_name;
    @SerializedName("neighborhood_name")
    @Expose
    private String neighborhood_name;
    @SerializedName("owner_mobile")
    @Expose
    private String ownerMobile;
    @SerializedName("lounges_number")
    @Expose
    private Integer loungesNumber;
    @SerializedName("rooms_number")
    @Expose
    private Integer roomsNumber;
    @SerializedName("bathrooms_number")
    @Expose
    private Integer bathroomsNumber;
    @SerializedName("boards_number")
    @Expose
    private Integer boardsNumber;
    @SerializedName("kitchen_number")
    @Expose
    private Integer kitchenNumber;
    @SerializedName("dining_rooms_number")
    @Expose
    private Integer diningRoomsNumber;
    @SerializedName("finishing_type")
    @Expose
    private String finishingType;
    @SerializedName("interface")
    @Expose
    private String _interface;
    @SerializedName("social_status")
    @Expose
    private String socialStatus;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lan")
    @Expose
    private String lan;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("is_resident")
    @Expose
    private Object isResident;
    @SerializedName("is_checked")
    @Expose
    private Object isChecked;
    @SerializedName("is_insured")
    @Expose
    private Object isInsured;
    @SerializedName("exclusive_contract_file")
    @Expose
    private Object exclusiveContractFile;
    @SerializedName("is_rent")
    @Expose
    private Object isRent;
    @SerializedName("rent_type")
    @Expose
    private Object rentType;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("operation_type_name")
    @Expose
    private String operationTypeName;


    @SerializedName("operation_type")
    @Expose
    private operation_type operation_type;
    @SerializedName("estate_type_name")
    @Expose
    private String estate_type_name;
    @SerializedName("seen_count")
    @Expose
    private String seen_count;
    @SerializedName("estate_type")
    @Expose
    private estate_type estate_type;
    @SerializedName("estate_file")
    @Expose
    private List<estate_file> estate_file;

    @SerializedName("comforts")
    @Expose
    private List<ComfortModules> comforts;


    public String getAddress() {
        return address;
    }

    public String getFirst_image() {
        return first_image;
    }

    boolean is_selected = false;

    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }

    public boolean getIs_selected() {
        return is_selected;
    }

    public String getSeen_count() {
        return seen_count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Integer operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public List<ComfortModules> getComforts() {
        return comforts;
    }

    public String get_interface() {
        return _interface;
    }

    public List<HomeModules_aqares.estate_file> getEstate_file() {
        return estate_file;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEstateTypeId() {
        return estateTypeId;
    }

    public void setEstateTypeId(Integer estateTypeId) {
        this.estateTypeId = estateTypeId;
    }

    public String getInstrumentNumber() {
        return instrumentNumber;
    }

    public void setInstrumentNumber(String instrumentNumber) {
        this.instrumentNumber = instrumentNumber;
    }


    public String getPaceNumber() {
        return paceNumber;
    }

    public void setPaceNumber(String paceNumber) {
        this.paceNumber = paceNumber;
    }

    public String getPlannedNumber() {
        return plannedNumber;
    }

    public void setPlannedNumber(String plannedNumber) {
        this.plannedNumber = plannedNumber;
    }

    public String getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(String totalArea) {
        this.totalArea = totalArea;
    }

    public Integer getEstateAge() {
        return estateAge;
    }

    public void setEstateAge(Integer estateAge) {
        this.estateAge = estateAge;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getStreetView() {
        return streetView;
    }

    public void setStreetView(String streetView) {
        this.streetView = streetView;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMeterPrice() {
        return meterPrice;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getNeighborhood_name() {
        return neighborhood_name;
    }

    public void setMeterPrice(String meterPrice) {
        this.meterPrice = meterPrice;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public Integer getLoungesNumber() {
        return loungesNumber;
    }

    public void setLoungesNumber(Integer loungesNumber) {
        this.loungesNumber = loungesNumber;
    }

    public Integer getRoomsNumber() {
        return roomsNumber;
    }

    public void setRoomsNumber(Integer roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public Integer getBathroomsNumber() {
        return bathroomsNumber;
    }

    public void setBathroomsNumber(Integer bathroomsNumber) {
        this.bathroomsNumber = bathroomsNumber;
    }

    public Integer getBoardsNumber() {
        return boardsNumber;
    }

    public void setBoardsNumber(Integer boardsNumber) {
        this.boardsNumber = boardsNumber;
    }

    public Integer getKitchenNumber() {
        return kitchenNumber;
    }

    public void setKitchenNumber(Integer kitchenNumber) {
        this.kitchenNumber = kitchenNumber;
    }

    public Integer getDiningRoomsNumber() {
        return diningRoomsNumber;
    }

    public void setDiningRoomsNumber(Integer diningRoomsNumber) {
        this.diningRoomsNumber = diningRoomsNumber;
    }

    public String getFinishingType() {
        return finishingType;
    }

    public void setFinishingType(String finishingType) {
        this.finishingType = finishingType;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public String getSocialStatus() {
        return socialStatus;
    }

    public void setSocialStatus(String socialStatus) {
        this.socialStatus = socialStatus;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLan() {
        return lan;
    }

    public void setLan(String lan) {
        this.lan = lan;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getIsResident() {
        return isResident;
    }

    public void setIsResident(Object isResident) {
        this.isResident = isResident;
    }

    public Object getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Object isChecked) {
        this.isChecked = isChecked;
    }

    public Object getIsInsured() {
        return isInsured;
    }

    public void setIsInsured(Object isInsured) {
        this.isInsured = isInsured;
    }

    public Object getExclusiveContractFile() {
        return exclusiveContractFile;
    }

    public void setExclusiveContractFile(Object exclusiveContractFile) {
        this.exclusiveContractFile = exclusiveContractFile;
    }

    public Object getIsRent() {
        return isRent;
    }

    public void setIsRent(Object isRent) {
        this.isRent = isRent;
    }

    public Object getRentType() {
        return rentType;
    }

    public void setRentType(Object rentType) {
        this.rentType = rentType;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getOperationTypeName() {
        return operationTypeName;
    }

    public void setOperationTypeName(String operationTypeName) {
        this.operationTypeName = operationTypeName;
    }


    public String getEstate_type_name() {
        return estate_type_name;
    }

    public HomeModules_aqares.estate_type getEstate_type() {
        return estate_type;
    }


    public void setOperation_type(HomeModules_aqares.operation_type operation_type) {
        this.operation_type = operation_type;
    }

    public HomeModules_aqares.operation_type getOperation_type() {
        return operation_type;
    }

    public class operation_type {
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
