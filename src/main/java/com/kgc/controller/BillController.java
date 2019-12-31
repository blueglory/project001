package com.kgc.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.dao.BillMapper;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Provider;
import com.kgc.service.BillService;
import com.kgc.service.UserService;
import com.kgc.util.Data;
import com.sun.deploy.net.HttpResponse;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.message.SetAppletSizeMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sys")
public class BillController {
    @Resource
    BillService billService;

    @RequestMapping(value = "/bill")
    public String bill(HttpSession session,
                       @RequestParam(required = false) Integer pageIndex,
                       @RequestParam(required = false) String productName,
                       @RequestParam(required = false) Integer providerId,
                       @RequestParam(required = false) Integer isPayment) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        Page<Bill> page = billService.selectAll(pageIndex, 5, productName, providerId, isPayment);
        List<Provider> providers = billService.selectAllP();
        session.setAttribute("productName", productName);
        session.setAttribute("providerId", providerId);
        session.setAttribute("isPayment", isPayment);
        session.setAttribute("plist", providers);
        session.setAttribute("blist", page.getRecords());
        session.setAttribute("page", page);
        return "/bill/billlist";
    }

    @RequestMapping("/billadd")
    public String addbill(HttpSession session) {
        List<Provider> pList = billService.selectAllP();
        session.setAttribute("pList", pList);
        return "/bill/billadd";
    }

    @RequestMapping("/dobilladd")
    public String doaddbill(String billCode,
                            String productName,
                            String productUnit,
                            Integer providerId,
                            @RequestParam(required = false) Integer productCount,
                            @RequestParam(required = false) Long totalPrice,
                            Integer ispayment) {
        int i = billService.add(billCode, productName, productUnit, providerId, productCount, totalPrice, ispayment);
        if (i == 1) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billadd";
        }

    }

    @RequestMapping("/billmodify")
    public String billmodify(HttpSession session, @RequestParam Integer billid) {
        Bill bill = billService.selectById(billid);
        session.setAttribute("bill", bill);
        List<Provider> providerList = billService.selectAllP();
        session.setAttribute("plist", providerList);
        return "/bill/billmodify";
    }

    @RequestMapping("/dobillmodify")
    public String domodify(Integer id,
                           String billCode,
                           String productName,
                           String productUnit,
                           Integer providerId,
                           @RequestParam(required = false) Integer productCount,
                           @RequestParam(required = false) Long totalPrice,
                           Integer isPayment) {
        int i = billService.modify(id, billCode, productName, productUnit, providerId, productCount, totalPrice, isPayment);
        System.out.println("i" + i);
        if (i > 0) {
            return "redirect:/sys/bill";
        } else {
            return "redirect:/sys/billmodify";
        }
    }

    Data data = new Data();
    @RequestMapping("/billdel")
    @ResponseBody
    public Object del(Integer billid) {
        int i = 0;
        if (billid != null) {
            i = billService.delById(billid);
        }
        if (i == 1) {
            data.setDelResult("true");
            return data;
        } else if(i == 0){
            data.setDelResult("false");
            return data;
        } else{
            data.setDelResult("notexist");
            return data;
        }

    }

    @RequestMapping("/billview")
    public String billview(HttpSession session, @RequestParam Integer billid) {
        Bill bill = billService.selectById(billid);
        List<Provider> providers = billService.selectAllP();
        for (Provider pro : providers) {
            if (pro.getId() == bill.getProviderid()) {
                Provider provider = new Provider();
                provider.setProname(pro.getProname());
                bill.setProvider(provider);
                break;
            }
        }
        session.setAttribute("bill", bill);
        return "bill/billview";
    }
}
