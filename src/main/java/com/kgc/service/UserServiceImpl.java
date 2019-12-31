package com.kgc.service;

import com.kgc.dao.BillMapper;
import com.kgc.dao.RoleMapper;
import com.kgc.dao.UserMapper;
import com.kgc.pojo.Bill;
import com.kgc.pojo.Role;
import com.kgc.pojo.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper mapper;
    @Override
    public User selectByCode(String name, String password) {
        User user = new User();
        user.setUsercode(name);
        User u = mapper.selectOne(user);
        if (u!=null && u.getUserpassword().equals(password)) {
            return u;
        } else {
            return null;
        }
    }

}
