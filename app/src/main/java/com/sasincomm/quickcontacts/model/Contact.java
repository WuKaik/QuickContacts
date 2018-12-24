package com.sasincomm.quickcontacts.model;

/**
 * 作者：wk on 2018/12/24.
 */
public class Contact {
    private int id;
    private Long photoId;
    private String name;
    private String phoneNum;
    private String sortKey;
    private String email;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Contact: "
                +"id: "+id
                +"photoId: "+photoId
                +"phoneNum: "+phoneNum
                +"sortKey: "+sortKey
                +"email: "+email
                +"address: "+address;
    }
}
