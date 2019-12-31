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
public class BillServiceImpl implements BillService {
    @Resource
    BillMapper billMapper;
    @Resource
    private ProviderMapper providerMapper;

    @Override
    public Page<Bill> selectAll(Integer pageNum, Integer pageSize, String produceName, Integer providerId, Integer isPayment) {
        Page<Bill> page = new Page<Bill>(pageNum, pageSize);
        EntityWrapper wrapper = new EntityWrapper();
        if (produceName != null && !"".equals(produceName)) {
            wrapper.like("productname", produceName);
        }
        if (providerId != null && providerId != 0) {
            wrapper.eq("providerid", providerId);
        }
        if (isPayment != null && isPayment != 0) {
            wrapper.eq("ispayment", isPayment);
        }
        List<Bill> list = billMapper.selectPage(page, wrapper);
        List<Provider> providerList = providerMapper.selectList(null);
        for (Bill bill : list) {
            for (Provider p : providerList) {
                if (p.getId().equals(bill.getProviderid())) {
                    bill.setProvider(p);
                    break;
                }
            }
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Provider> selectAllP() {
        EntityWrapper wrapper = new EntityWrapper();
        List<Provider> providers = providerMapper.selectList(wrapper);
        return providers;
    }

    @Override
    public int add(String billCode, String productName, String productUnit, Integer providerId, Integer productCount, Long totalPrice, Integer isPayment) {
        Bill bill = new Bill();
        bill.setBillcode(billCode);
        bill.setProductname(productName);
        bill.setProductunit(productUnit);
        bill.setProviderid(providerId);
        bill.setTotalprice(totalPrice);
        bill.setProductcount(productCount);
        bill.setIspayment(isPayment);
        return billMapper.insert(bill);
    }

    @Override
    public Bill selectById(Integer id) {
        Bill bill = billMapper.selectById(id);
        return bill;
    }

    @Override
    public int modify(Integer id, String billCode, String productName, String productUnit, Integer providerId, Integer productCount, Long totalPrice, Integer isPayment) {
        Bill bill = new Bill();
        bill.setId(id);
        bill.setBillcode(billCode);
        bill.setProductname(productName);
        bill.setProductunit(productUnit);
        bill.setProviderid(providerId);
        bill.setTotalprice(totalPrice);
        bill.setProductcount(productCount);
        bill.setIspayment(isPayment);
        billMapper.updateById(bill);
        return billMapper.updateById(bill);
    }

    @Override
    public int delById(Integer id) {
        Bill bill = billMapper.selectById(id);
        Integer i = billMapper.deleteById(id);
        if(bill ==null){
        return 3;
        }
        return i;
    }

}
