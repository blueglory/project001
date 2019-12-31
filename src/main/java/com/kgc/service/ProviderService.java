package com.kgc.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.pojo.Provider;
import sun.net.idn.StringPrep;

import java.util.List;

public interface ProviderService {
    Page<Provider> selectAllP(String queryProCode, String queryProName, Integer pageIndex);
    Provider selectById(Integer id);
    int update(Provider provider);
    int add(Provider provider);
    int delById(Integer proid);
}
