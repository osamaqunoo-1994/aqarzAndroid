package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeferredInstallment {
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
    @SerializedName("stumble_amount")
    @Expose
    private Object stumbleAmount;
    @SerializedName("financing_body")
    @Expose
    private Object financingBody;
    @SerializedName("previous_financial_failures")
    @Expose
    private Object previousFinancialFailures;
    @SerializedName("engagements")
    @Expose
    private Object engagements;
    @SerializedName("personal_financing_engagements")
    @Expose
    private Object personalFinancingEngagements;
    @SerializedName("lease_finance_engagements")
    @Expose
    private Object leaseFinanceEngagements;
    @SerializedName("credit_card_engagements")
    @Expose
    private Object creditCardEngagements;
    @SerializedName("tenant_name")
    @Expose
    private String tenantName;
    @SerializedName("employer_name")
    @Expose
    private Object employerName;
    @SerializedName("tenant_mobile")
    @Expose
    private String tenantMobile;
    @SerializedName("tenant_identity_number")
    @Expose
    private String tenantIdentityNumber;
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
    private String tenantSalaryBankId;
    @SerializedName("is_salary_adapter_on")
    @Expose
    private Integer isSalaryAdapterOn;
    @SerializedName("status")
    @Expose
    private String status;
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
    private String tenantCityName;
    @SerializedName("status_name")
    @Expose
    private String statusName;

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

    public Object getStumbleAmount() {
        return stumbleAmount;
    }

    public void setStumbleAmount(Object stumbleAmount) {
        this.stumbleAmount = stumbleAmount;
    }

    public Object getFinancingBody() {
        return financingBody;
    }

    public void setFinancingBody(Object financingBody) {
        this.financingBody = financingBody;
    }

    public Object getPreviousFinancialFailures() {
        return previousFinancialFailures;
    }

    public void setPreviousFinancialFailures(Object previousFinancialFailures) {
        this.previousFinancialFailures = previousFinancialFailures;
    }

    public Object getEngagements() {
        return engagements;
    }

    public void setEngagements(Object engagements) {
        this.engagements = engagements;
    }

    public Object getPersonalFinancingEngagements() {
        return personalFinancingEngagements;
    }

    public void setPersonalFinancingEngagements(Object personalFinancingEngagements) {
        this.personalFinancingEngagements = personalFinancingEngagements;
    }

    public Object getLeaseFinanceEngagements() {
        return leaseFinanceEngagements;
    }

    public void setLeaseFinanceEngagements(Object leaseFinanceEngagements) {
        this.leaseFinanceEngagements = leaseFinanceEngagements;
    }

    public Object getCreditCardEngagements() {
        return creditCardEngagements;
    }

    public void setCreditCardEngagements(Object creditCardEngagements) {
        this.creditCardEngagements = creditCardEngagements;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Object getEmployerName() {
        return employerName;
    }

    public void setEmployerName(Object employerName) {
        this.employerName = employerName;
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

    public String getTenantSalaryBankId() {
        return tenantSalaryBankId;
    }

    public void setTenantSalaryBankId(String tenantSalaryBankId) {
        this.tenantSalaryBankId = tenantSalaryBankId;
    }

    public Integer getIsSalaryAdapterOn() {
        return isSalaryAdapterOn;
    }

    public void setIsSalaryAdapterOn(Integer isSalaryAdapterOn) {
        this.isSalaryAdapterOn = isSalaryAdapterOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTenantCityName() {
        return tenantCityName;
    }

    public void setTenantCityName(String tenantCityName) {
        this.tenantCityName = tenantCityName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
