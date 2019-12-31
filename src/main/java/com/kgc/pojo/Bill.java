package com.kgc.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.Date;

@Data
public class Bill {
    private Integer id;
    private String productunit;
    private String billcode;
    private String productname;
    private String productdesc;
    private Integer productcount;
    private Long totalprice;
    private Integer ispayment;
    private Integer providerid;
    @TableField(exist = false)
    private Provider provider;
    private Date creationdate;

}
