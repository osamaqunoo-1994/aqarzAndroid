package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class price_range {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("estate_price_range")
    @Expose
    private String estatePriceRange;
    @SerializedName("area_range")
    @Expose
    private String area_range;
    @SerializedName("estate_price_from")
    @Expose
    private String estatePriceFrom;
    @SerializedName("estate_price_to")
    @Expose
    private String estatePriceTo;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstatePriceRange() {
        return estatePriceRange;
    }

    public void setEstatePriceRange(String estatePriceRange) {
        this.estatePriceRange = estatePriceRange;
    }

    public String getArea_range() {
        return area_range;
    }

    public String getEstatePriceFrom() {
        return estatePriceFrom;
    }

    public void setEstatePriceFrom(String estatePriceFrom) {
        this.estatePriceFrom = estatePriceFrom;
    }

    public String getEstatePriceTo() {
        return estatePriceTo;
    }

    public void setEstatePriceTo(String estatePriceTo) {
        this.estatePriceTo = estatePriceTo;
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
}
