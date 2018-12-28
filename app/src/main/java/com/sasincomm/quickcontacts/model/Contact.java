package com.sasincomm.quickcontacts.model;

import java.io.Serializable;

/**
 * 作者：wk on 2018/12/24.
 */
public class Contact implements Serializable{
    private int id;
    private int rawId;
    private Long photoId;
    private String name;
    private String phoneNum;
    private String sortKey;
    private String email;
    private String address;
    private String province;
    private String city;
    private String street;
    private String neighborhood;
    private String mainbox;
    private String postcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRawId() {
        return rawId;
    }

    public void setRawId(int rawId) {
        this.rawId = rawId;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getMainbox() {
        return mainbox;
    }

    public void setMainbox(String mainbox) {
        this.mainbox = mainbox;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Contact: "
                +"id: "+id
                +"rawId: "+rawId
                +"photoId: "+photoId
                +"phoneNum: "+phoneNum
                +"sortKey: "+sortKey
                +"email: "+email
                +"address: "+address;
    }
}
