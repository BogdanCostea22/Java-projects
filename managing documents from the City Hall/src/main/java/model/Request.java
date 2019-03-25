package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Request")
public class Request {
    @Id
    @Column(name = "request_id")
    private String id;

    @Column(name = "status")
    private int available;

    @Column(name = "document_id")
    private String document_id;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "house_id")
    private String houseId;


    public Request() {
    }

    public Request(String id, int available, String document_id, String userId, String houseId) {
        this.id = id;
        this.available = available;
        this.document_id = document_id;
        this.userId = userId;
        this.houseId = houseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getDocument_id() {
        return document_id;
    }

    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id='" + id + '\'' +
                ", available=" + available +
                ", document_id='" + document_id + '\'' +
                ", userId='" + userId + '\'' +
                ", houseId='" + houseId + '\'' +
                '}';
    }
}
