package com.agenda.model;

import javax.persistence.*;

@Entity
@Table(name = "UserData")

public class UserData {
    // @Id
    // @GeneratedValue
    // private Long id;

    // @Column(name = "Username")
    // private String username;

    // @Column(name = "Userpass")
    // private String userpass;

    // @Column(name = "IsAdm")
    // private Boolean isAdm;

    // @Column(name = "IsRandPass")
    // private Boolean IsRandPass;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "Username")
    private String username;

    @Column(name = "Userpass")
    private String userpass;

    @Column(name = "IsAdm")
    private Boolean isAdm;
    
    @Column(name = "IsRandPass")
    private Boolean isRandPass;

    public Long getId() {
        return this.id;
    }
    public void setId(Long value) {
        this.id = value;
    }
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String value) {
        this.username = value;
    }
    public String getUserpass() {
        return this.userpass;
    }
    public void setUserpass(String value) {
        this.userpass = value;
    }
    public Boolean getIsAdm() {
        return this.isAdm;
    }
    public void setIsAdm(Boolean value) {
        this.isAdm = value;
    }
    public Boolean getIsRandPass() {
        return this.isRandPass;
    }
    public void setIsRandPass(Boolean value) {
        this.isRandPass = value;
    }
}
