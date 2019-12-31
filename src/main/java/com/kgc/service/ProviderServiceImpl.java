package com.kgc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.dao.BillMapper;
import com.kgc.dao.ProviderMapper;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Provider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    ProviderMapper providerMapper;

    @Override
    public Page<Provider> selectAllP(String queryProCode, String queryProName, Integer pageIndex) {
        EntityWrapper wrapper = new EntityWrapper();
        Page page = new Page(pageIndex,5);
        if (queryProCode != null) {
            wrapper.like("procode", queryProCode);
        }
        if (queryProName != null) {
            wrapper.like("proname", queryProName);
        }
        List<Provider> providers = providerMapper.selectPage(page,wrapper);
        page.setRecords(providers);
        return page;
    }

    @Override
    public Provider selectById(Integer id) {
        Provider provider = providerMapper.selectById(id);
        return provider;
    }

    @Override
    public int update(Provider provider) {
        Integer i = providerMapper.updateById(provider);
        return i;
    }

    @Override
    public int add(Provider provider) {
        Integer insert = providerMapper.insert(provider);
        return insert;
    }

    @Resource
    BillMapper billMapper;

    @Override
    public int delById(Integer proid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("providerid", proid);
        List list = billMapper.selectList(wrapper);
        if (list.size() != 0) {
            return list.size();
        } else {
            Integer i = providerMapper.deleteById(proid);
            System.out.println(i+"iiiii");
            return i;
        }
    }
}
