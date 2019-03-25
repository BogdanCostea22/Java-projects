package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="House")
public class House {
    @Id
    @Column(name ="house_id")
    private String houseId;

    @Column(name = "user_id_id")
    private String userId;

    public House() {
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public House(String houseId, String userId) {
        this.houseId = houseId;
        this.userId = userId;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "House{" +
                "houseId='" + houseId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
