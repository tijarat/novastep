package com.step.nova.dto;

import java.util.Date;

public class RegisterRequest 
{
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
    private Long roleId;

    private Date dateOfBirth;
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhoneNumber() {return phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
    public String getPassword() {        return password;    }
    public void setPassword(String password) {        this.password = password;    }
    public Long getRoleId() {        return roleId;    }
    public void setRoleId(Long roleId) {        this.roleId = roleId;    }
    public Date getDateOfBirth() {        return dateOfBirth;    }
    public void setDateOfBirth(Date dateOfBirth) {        this.dateOfBirth = dateOfBirth;    }
}