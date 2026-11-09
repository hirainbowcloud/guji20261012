package dao;

//Entity Class
public class Supplier {
    private int supId;
    private String supName;
    private String street;
    private String city;
    private String state;
    private String zip;

    public Supplier() {}

    public Supplier(int supId, String supName, String street, String city, String state, String zip) {
        this.supId = supId;
        this.supName = supName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public int getSupId() {
        return supId;
    }

    public void setSupId(int supId) {
        this.supId = supId;
    }

    public String getSupName() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Supplier{supId=" + supId + ", supName='" + supName + "', street='" + street
                + "', city='" + city + "', state='" + state + "', zip='" + zip + "'}";
    }
}