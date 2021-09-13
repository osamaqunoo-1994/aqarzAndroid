package sa.aqarz.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RateDetails {
    @SerializedName("rate")
    @Expose
    private rate rate;
    @SerializedName("notes")
    @Expose
    private List<notes> notes;

    public List<RateDetails.notes> getNotes() {
        return notes;
    }

    public RateDetails.rate getRate() {
        return rate;
    }

    public class notes {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("rate")
        @Expose
        private Integer rate;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("estate_id")
        @Expose
        private Integer estateId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public Integer getRate() {
            return rate;
        }

        public void setRate(Integer rate) {
            this.rate = rate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getEstateId() {
            return estateId;
        }

        public void setEstateId(Integer estateId) {
            this.estateId = estateId;
        }

    }

    public class rate {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("estate_id")
        @Expose
        private Integer estateId;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("one")
        @Expose
        private String one;
        @SerializedName("two")
        @Expose
        private String two;
        @SerializedName("three")
        @Expose
        private String three;
        @SerializedName("four")
        @Expose
        private String four;
        @SerializedName("five")
        @Expose
        private String five;
        @SerializedName("rate")
        @Expose
        private String rate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getEstateId() {
            return estateId;
        }

        public void setEstateId(Integer estateId) {
            this.estateId = estateId;
        }

        public Integer getTotal() {
            return total;
        }

        public String getRate() {
            return rate;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public String getThree() {
            return three;
        }

        public void setThree(String three) {
            this.three = three;
        }

        public String getFour() {
            return four;
        }

        public void setFour(String four) {
            this.four = four;
        }

        public String getFive() {
            return five;
        }

        public void setFive(String five) {
            this.five = five;
        }

    }
}
