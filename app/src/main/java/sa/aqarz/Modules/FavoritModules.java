package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavoritModules {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("estate_pace_number")
    @Expose
    private String estatePaceNumber;
    @SerializedName("estate_planned_number")
    @Expose
    private String estatePlannedNumber;
    @SerializedName("estate_total_area")
    @Expose
    private String estateTotalArea;
    @SerializedName("estate_estate_age")
    @Expose
    private Integer estateEstateAge;
    @SerializedName("estate_floor_number")
    @Expose
    private Integer estateFloorNumber;
    @SerializedName("estate_street_view")
    @Expose
    private String estateStreetView;
    @SerializedName("estate_total_price")
    @Expose
    private String estateTotalPrice;
    @SerializedName("estate_meter_price")
    @Expose
    private String estateMeterPrice;
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
    @SerializedName("estate_neighborhood")
    @Expose
    private Object estateNeighborhood;
    @SerializedName("estate_exclusive_contract_file")
    @Expose
    private Object estateExclusiveContractFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstatePaceNumber() {
        return estatePaceNumber;
    }

    public void setEstatePaceNumber(String estatePaceNumber) {
        this.estatePaceNumber = estatePaceNumber;
    }

    public String getEstatePlannedNumber() {
        return estatePlannedNumber;
    }

    public void setEstatePlannedNumber(String estatePlannedNumber) {
        this.estatePlannedNumber = estatePlannedNumber;
    }

    public String getEstateTotalArea() {
        return estateTotalArea;
    }

    public void setEstateTotalArea(String estateTotalArea) {
        this.estateTotalArea = estateTotalArea;
    }

    public Integer getEstateEstateAge() {
        return estateEstateAge;
    }

    public void setEstateEstateAge(Integer estateEstateAge) {
        this.estateEstateAge = estateEstateAge;
    }

    public Integer getEstateFloorNumber() {
        return estateFloorNumber;
    }

    public void setEstateFloorNumber(Integer estateFloorNumber) {
        this.estateFloorNumber = estateFloorNumber;
    }

    public String getEstateStreetView() {
        return estateStreetView;
    }

    public void setEstateStreetView(String estateStreetView) {
        this.estateStreetView = estateStreetView;
    }

    public String getEstateTotalPrice() {
        return estateTotalPrice;
    }

    public void setEstateTotalPrice(String estateTotalPrice) {
        this.estateTotalPrice = estateTotalPrice;
    }

    public String getEstateMeterPrice() {
        return estateMeterPrice;
    }

    public void setEstateMeterPrice(String estateMeterPrice) {
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

    public Object getEstateNeighborhood() {
        return estateNeighborhood;
    }

    public void setEstateNeighborhood(Object estateNeighborhood) {
        this.estateNeighborhood = estateNeighborhood;
    }

    public Object getEstateExclusiveContractFile() {
        return estateExclusiveContractFile;
    }

    public void setEstateExclusiveContractFile(Object estateExclusiveContractFile) {
        this.estateExclusiveContractFile = estateExclusiveContractFile;
    }
}
