package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class financeModules {

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
    @SerializedName("job_type")
    @Expose
    private String jobType;
    @SerializedName("finance_interval")
    @Expose
    private String financeInterval;
    @SerializedName("job_start_date")
    @Expose
    private String jobStartDate;
    @SerializedName("estate_price")
    @Expose
    private Integer estatePrice;
    @SerializedName("engagements")
    @Expose
    private String engagements;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("identity_number")
    @Expose
    private String identityNumber;
    @SerializedName("identity_file")
    @Expose
    private String identityFile;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("total_salary")
    @Expose
    private String totalSalary;
    @SerializedName("available_amount")
    @Expose
    private String availableAmount;
    @SerializedName("national_address")
    @Expose
    private Object nationalAddress;
    //    @SerializedName("national_address_file")
//    @Expose
//    private NationalAddressFile nationalAddressFile;
    @SerializedName("building_number")
    @Expose
    private Object buildingNumber;
    @SerializedName("street_name")
    @Expose
    private Object streetName;
    @SerializedName("neighborhood_name")
    @Expose
    private Object neighborhoodName;
    @SerializedName("building_city_name")
    @Expose
    private Object buildingCityName;
    @SerializedName("birthday")
    @Expose
    private Object birthday;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("national_address_display")
    @Expose
    private Object nationalAddressDisplay;
    @SerializedName("additional_number")
    @Expose
    private Object additionalNumber;
    @SerializedName("unit_number")
    @Expose
    private Object unitNumber;
    @SerializedName("solidarity_partner")
    @Expose
    private String solidarityPartner;
    @SerializedName("solidarity_salary")
    @Expose
    private String solidaritySalary;
    @SerializedName("offer_numbers")
    @Expose
    private Object offerNumbers;
    @SerializedName("bank_id")
    @Expose
    private Integer bankId;
    @SerializedName("is_subsidized_property")
    @Expose
    private String isSubsidizedProperty;
    @SerializedName("is_first_home")
    @Expose
    private String isFirstHome;
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
    @SerializedName("estate_type_name")
    @Expose
    private String estateTypeName;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("bank")
    @Expose
    private BankModules bank;
    @SerializedName("city")
    @Expose
    private CityModules city;
    @SerializedName("estate_type")
    @Expose
    private TypeModules estate_type;
    @SerializedName("operation_type")
    @Expose
    private TypeModules operation_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public TypeModules getOperation_type() {
        return operation_type;
    }

    public TypeModules getEstate_type() {
        return estate_type;
    }

    public BankModules getBank() {
        return bank;
    }

    public CityModules getCity() {
        return city;
    }

    public Integer getOperationTypeId() {
        return operationTypeId;
    }

    public void setOperationTypeId(Integer operationTypeId) {
        this.operationTypeId = operationTypeId;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getFinanceInterval() {
        return financeInterval;
    }

    public void setFinanceInterval(String financeInterval) {
        this.financeInterval = financeInterval;
    }

    public String getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(String jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public Integer getEstatePrice() {
        return estatePrice;
    }

    public void setEstatePrice(Integer estatePrice) {
        this.estatePrice = estatePrice;
    }

    public String getEngagements() {
        return engagements;
    }

    public void setEngagements(String engagements) {
        this.engagements = engagements;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getIdentityFile() {
        return identityFile;
    }

    public void setIdentityFile(String identityFile) {
        this.identityFile = identityFile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(String totalSalary) {
        this.totalSalary = totalSalary;
    }

    public String getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(String availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Object getNationalAddress() {
        return nationalAddress;
    }

    public void setNationalAddress(Object nationalAddress) {
        this.nationalAddress = nationalAddress;
    }


    public Object getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Object buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Object getStreetName() {
        return streetName;
    }

    public void setStreetName(Object streetName) {
        this.streetName = streetName;
    }

    public Object getNeighborhoodName() {
        return neighborhoodName;
    }

    public void setNeighborhoodName(Object neighborhoodName) {
        this.neighborhoodName = neighborhoodName;
    }

    public Object getBuildingCityName() {
        return buildingCityName;
    }

    public void setBuildingCityName(Object buildingCityName) {
        this.buildingCityName = buildingCityName;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getNationalAddressDisplay() {
        return nationalAddressDisplay;
    }

    public void setNationalAddressDisplay(Object nationalAddressDisplay) {
        this.nationalAddressDisplay = nationalAddressDisplay;
    }

    public Object getAdditionalNumber() {
        return additionalNumber;
    }

    public void setAdditionalNumber(Object additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

    public Object getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(Object unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getSolidarityPartner() {
        return solidarityPartner;
    }

    public void setSolidarityPartner(String solidarityPartner) {
        this.solidarityPartner = solidarityPartner;
    }

    public String getSolidaritySalary() {
        return solidaritySalary;
    }

    public void setSolidaritySalary(String solidaritySalary) {
        this.solidaritySalary = solidaritySalary;
    }

    public Object getOfferNumbers() {
        return offerNumbers;
    }

    public void setOfferNumbers(Object offerNumbers) {
        this.offerNumbers = offerNumbers;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getIsSubsidizedProperty() {
        return isSubsidizedProperty;
    }

    public void setIsSubsidizedProperty(String isSubsidizedProperty) {
        this.isSubsidizedProperty = isSubsidizedProperty;
    }

    public String getIsFirstHome() {
        return isFirstHome;
    }

    public void setIsFirstHome(String isFirstHome) {
        this.isFirstHome = isFirstHome;
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

    public String getEstateTypeName() {
        return estateTypeName;
    }

    public void setEstateTypeName(String estateTypeName) {
        this.estateTypeName = estateTypeName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


}
