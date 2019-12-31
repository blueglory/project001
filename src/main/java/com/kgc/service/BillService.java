package com.kgc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Provider;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.concurrent.Delayed;

public interface BillService {
    Page<Bill> selectAll(Integer pageNum, Integer pageSize, String produceName, Integer providerId, Integer isPayment);

    List<Provider> selectAllP();

    int add(String billCode,
            String productName,
            String productUnit,
            Integer providerId,
            Integer productCount,
            Long totalPrice,
            Integer isPayment);

    Bill selectById(Integer id);
    int modify(Integer id,
               String billCode,
               String productName,
               String productUnit,
               Integer providerId,
               Integer productCount,
               Long totalPrice,
               Integer isPayment);
    int delById(Integer id);
}
