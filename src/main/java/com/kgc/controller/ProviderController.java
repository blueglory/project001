package com.kgc.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.kgc.pojo.Provider;
import com.kgc.service.BillService;
import com.kgc.service.ProviderService;
import com.kgc.util.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/provider")
public class ProviderController {
    @Resource
    ProviderService providerService;

    @RequestMapping("/list")
    public String provider(HttpSession session,
                           @RequestParam(required = false) String queryProCode,
                           @RequestParam(required = false) String queryProName,
                           @RequestParam(required = false) Integer pageIndex) {
        if(pageIndex == null){
            pageIndex = 1;
        }
        Page<Provider> page = providerService.selectAllP(queryProCode, queryProName, pageIndex);
        session.setAttribute("proList", page.getRecords());
        session.setAttribute("page",page);
        session.setAttribute("queryProCode",queryProCode);
        session.setAttribute("queryProName",queryProName);
        return "/pro/providerlist";
    }

    @RequestMapping("proview")
    public String view(HttpSession session, @RequestParam Integer proid) {
        Provider provider = providerService.selectById(proid);
        session.setAttribute("provider", provider);
        return "/pro/providerview";
    }

    @RequestMapping("providermodify")
    public String modify(HttpSession session, @RequestParam Integer proid) {
        Provider provider = providerService.selectById(proid);
        session.setAttribute("provider", provider);
        return "/pro/providermodify";
    }

    @RequestMapping("providermodifysave")
    public String save(Provider provider) {
        int i = providerService.update(provider);
        if (i > 0) {
            return "redirect:/provider/list";
        } else {
            return "redirect:/provider/providermodify";
        }
    }

    @RequestMapping("provideradd")
    public String add() {
        return "pro/provideradd";
    }
    @RequestMapping("saveprovideradd")
    public String doadd(String procode,
                        String proname,
                        String procontact,
                        String prophone,
                        @RequestParam(required = false)String proaddress,
                        @RequestParam(required = false)String profax,
                        @RequestParam(required = false) String prodesc){
        Provider provider = new Provider();
        provider.setProcode(procode);
        provider.setProname(proname);
        provider.setProcontact(procontact);
        provider.setProaddress(proaddress);
        provider.setProfax(profax);
        provider.setProdesc(prodesc);
        provider.setProphone(prophone);
        System.out.println("provider:"+provider);
            int add = providerService.add(provider);
            if (add > 0) {
                return "redirect:/provider/list";
            } else {
                return "redirect:/provider/provideradd";
            }

    }
    @RequestMapping("/delprovider")
    @ResponseBody
    public Object del(Integer proid){
        System.out.println(proid+"proid");
        int i = providerService.delById(proid);
        if(i==0) {
            Data data = new Data();
            data.setDelResult("false");
            return data;
        } else if(i==1){
            Data data = new Data();
            data.setDelResult("true");
            return data;
        } else {
            Data data = new Data();
            String istr = Integer.toString(i);
            data.setDelResult(istr);
            return data;
        }
    }
}
