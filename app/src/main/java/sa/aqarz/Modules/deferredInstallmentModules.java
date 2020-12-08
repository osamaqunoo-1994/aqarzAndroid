package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class deferredInstallmentModules {

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
    @SerializedName("contract_interval")
    @Expose
    private Integer contractInterval;
    @SerializedName("rent_price")
    @Expose
    private Integer rentPrice;
    @SerializedName("tenant_name")
    @Expose
    private String tenantName;
    @SerializedName("tenant_mobile")
    @Expose
    private String tenantMobile;
    @SerializedName("tenant_identity_number")
    @Expose
    private String tenantIdentityNumber;
    @SerializedName("tenant_identity_file")
    @Expose
    private String tenantIdentityFile;
    @SerializedName("tenant_birthday")
    @Expose
    private String tenantBirthday;
    @SerializedName("tenant_city_id")
    @Expose
    private String tenantCityId;
    @SerializedName("tenant_job_type")
    @Expose
    private String tenantJobType;
    @SerializedName("tenant_total_salary")
    @Expose
    private String tenantTotalSalary;
    @SerializedName("tenant_salary_bank_id")
    @Expose
    private Object tenantSalaryBankId;
//    @SerializedName("national_address_file")
//    @Expose
//    private NationalAddressFile nationalAddressFile;
    @SerializedName("building_number")
    @Expose
    private Object buildingNumber;
    @SerializedName("street_name")
    @Expose
    private Object streetName;
    @SerializedName("unit_name")
    @Expose
    private Object unitName;
    @SerializedName("neighborhood_name")
    @Expose
    private Object neighborhoodName;
    @SerializedName("building_city_name")
    @Expose
    private Object buildingCityName;
    @SerializedName("postal_code")
    @Expose
    private Object postalCode;
    @SerializedName("status")
    @Expose
    private Object status;
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
    @SerializedName("tenant_city_name")
    @Expose
    private Object tenantCityName;

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

    public Integer getContractInterval() {
        return contractInterval;
    }

    public void setContractInterval(Integer contractInterval) {
        this.contractInterval = contractInterval;
    }

    public Integer getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Integer rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantMobile() {
        return tenantMobile;
    }

    public void setTenantMobile(String tenantMobile) {
        this.tenantMobile = tenantMobile;
    }

    public String getTenantIdentityNumber() {
        return tenantIdentityNumber;
    }

    public void setTenantIdentityNumber(String tenantIdentityNumber) {
        this.tenantIdentityNumber = tenantIdentityNumber;
    }

    public String getTenantIdentityFile() {
        return tenantIdentityFile;
    }

    public void setTenantIdentityFile(String tenantIdentityFile) {
        this.tenantIdentityFile = tenantIdentityFile;
    }

    public String getTenantBirthday() {
        return tenantBirthday;
    }

    public void setTenantBirthday(String tenantBirthday) {
        this.tenantBirthday = tenantBirthday;
    }

    public String getTenantCityId() {
        return tenantCityId;
    }

    public void setTenantCityId(String tenantCityId) {
        this.tenantCityId = tenantCityId;
    }

    public String getTenantJobType() {
        return tenantJobType;
    }

    public void setTenantJobType(String tenantJobType) {
        this.tenantJobType = tenantJobType;
    }

    public String getTenantTotalSalary() {
        return tenantTotalSalary;
    }

    public void setTenantTotalSalary(String tenantTotalSalary) {
        this.tenantTotalSalary = tenantTotalSalary;
    }

    public Object getTenantSalaryBankId() {
        return tenantSalaryBankId;
    }

    public void setTenantSalaryBankId(Object tenantSalaryBankId) {
        this.tenantSalaryBankId = tenantSalaryBankId;
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

    public Object getUnitName() {
        return unitName;
    }

    public void setUnitName(Object unitName) {
        this.unitName = unitName;
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

    public Object getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Object postalCode) {
        this.postalCode = postalCode;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
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

    public Object getTenantCityName() {
        return tenantCityName;
    }

    public void setTenantCityName(Object tenantCityName) {
        this.tenantCityName = tenantCityName;
    }

}
