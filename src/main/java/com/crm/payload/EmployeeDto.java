package com.crm.payload;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class EmployeeDto {

    private Long id;

    @NotBlank
    @Size(min = 3, message = "At least 3 chars required")
    private String name;

    @Email
    private String emailId;

    @Size(min = 10, max = 10, message = "Should be 10 digits")
    private String mobile;
    //private Date date;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMobile() {
        return mobile;
    }

    /*public Date getDate() {
        return date;   }*/

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

   /* public void setDate(Date emp) {
    this.date = date;
    }*/
}


