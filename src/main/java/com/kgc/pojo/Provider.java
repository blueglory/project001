package com.kgc.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Provider {
    private Integer id;
    private String procode;
    private String proname;
    private String prodesc;
    private String procontact;
    private String prophone;
    private String profax;
    private Date creationdate;
    private String proaddress;

}
