package com.kgc.pojo;

import lombok.Data;

@Data
public class User{
    private Integer id;
    private String username;
    private String userpassword;
    private String usercode;
    private String phone;
    private String address;
    private Integer userrole;


}
