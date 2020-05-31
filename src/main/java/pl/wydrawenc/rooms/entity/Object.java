package pl.wydrawenc.rooms.entity;

import javax.persistence.*;

@Entity
@Table(name="object")
public class Object {

    @Id
    @Column(name="object_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int objectID;

    @Column(name="name")
    private String name;

    @Column(name="sh_desc")
    private String sh_desc;

    @Column(name="long_desc")
    private String long_desc;

    @Column(name="price_day")
    private int price_day;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userID;

    @Column(name="city")
    private String city;

    @Column(name="postal_code")
    private String postal_code;

    @Column(name="address")
    private String address;

    @Column(name="imgsrc")
    private String imgsrc;

    public Object() {
    }

    public Object(String name, String sh_desc, String long_desc, int price_day, User user, String city, String postal_code, String address, String imgsrc) {
        this.name = name;
        this.sh_desc = sh_desc;
        this.long_desc = long_desc;
        this.price_day = price_day;
        this.userID = user;
        this.city = city;
        this.postal_code = postal_code;
        this.address = address;
        this.imgsrc = imgsrc;
    }

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSh_desc() {
        return sh_desc;
    }

    public void setSh_desc(String sh_desc) {
        this.sh_desc = sh_desc;
    }

    public String getLong_desc() {
        return long_desc;
    }

    public void setLong_desc(String long_desc) {
        this.long_desc = long_desc;
    }

    public int getPrice_day() {
        return price_day;
    }

    public void setPrice_day(int price_day) {
        this.price_day = price_day;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User user_id) {
        this.userID = user_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
