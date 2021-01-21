package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyOfferModule {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("instument_number")
    @Expose
    private Object instumentNumber;
    @SerializedName("guarantees")
    @Expose
    private Object guarantees;
    @SerializedName("beneficiary_name")
    @Expose
    private String beneficiaryName;
    @SerializedName("estate_type_name")
    @Expose
    private String estate_type_name;
    @SerializedName("sale_type_name")
    @Expose
    private String sale_type_name;


    @SerializedName("beneficiary_mobile")
    @Expose
    private String beneficiaryMobile;
    @SerializedName("estate_id")
    @Expose
    private Integer estateId;
    @SerializedName("estate_pace_number")
    @Expose
    private Object estatePaceNumber;
    @SerializedName("estate_planned_number")
    @Expose
    private Object estatePlannedNumber;
    @SerializedName("estate_total_area")
    @Expose
    private String estateTotalArea;
    @SerializedName("estate_estate_age")
    @Expose
    private Object estateEstateAge;
    @SerializedName("estate_floor_number")
    @Expose
    private Object estateFloorNumber;
    @SerializedName("estate_street_view")
    @Expose
    private Object estateStreetView;
    @SerializedName("estate_total_price")
    @Expose
    private String estateTotalPrice;
    @SerializedName("estate_meter_price")
    @Expose
    private Object estateMeterPrice;
    @SerializedName("estate_lounges_number")
    @Expose
    private Integer estateLoungesNumber;
    @SerializedName("estate_rooms_number")
    @Expose
    private Integer estateRoomsNumber;
    @SerializedName("estate_bathrooms_number")
    @Expose
    private Integer estateBathroomsNumber;
    @SerializedName("estate_boards_number")
    @Expose
    private Integer estateBoardsNumber;
    @SerializedName("estate_kitchen_number")
    @Expose
    private Integer estateKitchenNumber;
    @SerializedName("estate_dining_rooms_number")
    @Expose
    private Integer estateDiningRoomsNumber;
    @SerializedName("estate_finishing_type")
    @Expose
    private String estateFinishingType;
    @SerializedName("estate_interface")
    @Expose
    private String estateInterface;
    @SerializedName("estate_note")
    @Expose
    private String estateNote;
    @SerializedName("estate_is_resident")
    @Expose
    private String estateIsResident;
    @SerializedName("estate_is_checked")
    @Expose
    private String estateIsChecked;
    @SerializedName("estate_is_insured")
    @Expose
    private String estateIsInsured;
    @SerializedName("estate_city")
    @Expose
    private String estateCity;
    @SerializedName("in_fav")
    @Expose
    private Integer inFav;
    @SerializedName("estate_neighborhood")
    @Expose
    private String estateNeighborhood;
    @SerializedName("estate_exclusive_contract_file")
    @Expose
    private Object estateExclusiveContractFile;
    @SerializedName("status_name")
    @Expose
    private Object statusName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("estate_attachment")
    @Expose
    private List<estate_attachment> estate_attachment;

    public Integer getId() {
        return id;
    }

    public List<MyOfferModule.estate_attachment> getEstate_attachment() {
        return estate_attachment;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEstate_type_name() {
        return estate_type_name;
    }

    public String getSale_type_name() {
        return sale_type_name;
    }

    public Object getInstumentNumber() {
        return instumentNumber;
    }

    public void setInstumentNumber(Object instumentNumber) {
        this.instumentNumber = instumentNumber;
    }

    public Object getGuarantees() {
        return guarantees;
    }

    public void setGuarantees(Object guarantees) {
        this.guarantees = guarantees;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryMobile() {
        return beneficiaryMobile;
    }

    public void setBeneficiaryMobile(String beneficiaryMobile) {
        this.beneficiaryMobile = beneficiaryMobile;
    }

    public Integer getEstateId() {
        return estateId;
    }

    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    public Object getEstatePaceNumber() {
        return estatePaceNumber;
    }

    public void setEstatePaceNumber(Object estatePaceNumber) {
        this.estatePaceNumber = estatePaceNumber;
    }

    public Object getEstatePlannedNumber() {
        return estatePlannedNumber;
    }

    public void setEstatePlannedNumber(Object estatePlannedNumber) {
        this.estatePlannedNumber = estatePlannedNumber;
    }

    public String getEstateTotalArea() {
        return estateTotalArea;
    }

    public void setEstateTotalArea(String estateTotalArea) {
        this.estateTotalArea = estateTotalArea;
    }

    public Object getEstateEstateAge() {
        return estateEstateAge;
    }

    public void setEstateEstateAge(Object estateEstateAge) {
        this.estateEstateAge = estateEstateAge;
    }

    public Object getEstateFloorNumber() {
        return estateFloorNumber;
    }

    public void setEstateFloorNumber(Object estateFloorNumber) {
        this.estateFloorNumber = estateFloorNumber;
    }

    public Object getEstateStreetView() {
        return estateStreetView;
    }

    public void setEstateStreetView(Object estateStreetView) {
        this.estateStreetView = estateStreetView;
    }

    public String getEstateTotalPrice() {
        return estateTotalPrice;
    }

    public void setEstateTotalPrice(String estateTotalPrice) {
        this.estateTotalPrice = estateTotalPrice;
    }

    public Object getEstateMeterPrice() {
        return estateMeterPrice;
    }

    public void setEstateMeterPrice(Object estateMeterPrice) {
        this.estateMeterPrice = estateMeterPrice;
    }

    public Integer getEstateLoungesNumber() {
        return estateLoungesNumber;
    }

    public void setEstateLoungesNumber(Integer estateLoungesNumber) {
        this.estateLoungesNumber = estateLoungesNumber;
    }

    public Integer getEstateRoomsNumber() {
        return estateRoomsNumber;
    }

    public void setEstateRoomsNumber(Integer estateRoomsNumber) {
        this.estateRoomsNumber = estateRoomsNumber;
    }

    public Integer getEstateBathroomsNumber() {
        return estateBathroomsNumber;
    }

    public void setEstateBathroomsNumber(Integer estateBathroomsNumber) {
        this.estateBathroomsNumber = estateBathroomsNumber;
    }

    public Integer getEstateBoardsNumber() {
        return estateBoardsNumber;
    }

    public void setEstateBoardsNumber(Integer estateBoardsNumber) {
        this.estateBoardsNumber = estateBoardsNumber;
    }

    public Integer getEstateKitchenNumber() {
        return estateKitchenNumber;
    }

    public void setEstateKitchenNumber(Integer estateKitchenNumber) {
        this.estateKitchenNumber = estateKitchenNumber;
    }

    public Integer getEstateDiningRoomsNumber() {
        return estateDiningRoomsNumber;
    }

    public void setEstateDiningRoomsNumber(Integer estateDiningRoomsNumber) {
        this.estateDiningRoomsNumber = estateDiningRoomsNumber;
    }

    public String getEstateFinishingType() {
        return estateFinishingType;
    }

    public void setEstateFinishingType(String estateFinishingType) {
        this.estateFinishingType = estateFinishingType;
    }

    public String getEstateInterface() {
        return estateInterface;
    }

    public void setEstateInterface(String estateInterface) {
        this.estateInterface = estateInterface;
    }

    public String getEstateNote() {
        return estateNote;
    }

    public void setEstateNote(String estateNote) {
        this.estateNote = estateNote;
    }

    public String getEstateIsResident() {
        return estateIsResident;
    }

    public void setEstateIsResident(String estateIsResident) {
        this.estateIsResident = estateIsResident;
    }

    public String getEstateIsChecked() {
        return estateIsChecked;
    }

    public void setEstateIsChecked(String estateIsChecked) {
        this.estateIsChecked = estateIsChecked;
    }

    public String getEstateIsInsured() {
        return estateIsInsured;
    }

    public void setEstateIsInsured(String estateIsInsured) {
        this.estateIsInsured = estateIsInsured;
    }

    public String getEstateCity() {
        return estateCity;
    }

    public void setEstateCity(String estateCity) {
        this.estateCity = estateCity;
    }

    public Integer getInFav() {
        return inFav;
    }

    public void setInFav(Integer inFav) {
        this.inFav = inFav;
    }

    public String getEstateNeighborhood() {
        return estateNeighborhood;
    }

    public void setEstateNeighborhood(String  estateNeighborhood) {
        this.estateNeighborhood = estateNeighborhood;
    }

    public Object getEstateExclusiveContractFile() {
        return estateExclusiveContractFile;
    }

    public void setEstateExclusiveContractFile(Object estateExclusiveContractFile) {
        this.estateExclusiveContractFile = estateExclusiveContractFile;
    }

    public Object getStatusName() {
        return statusName;
    }

    public void setStatusName(Object statusName) {
        this.statusName = statusName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public class estate_attachment{
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("file")
        @Expose
        private String file;

        public Integer getId() {
            return id;
        }

        public String getFile() {
            return file;
        }
    }
}
