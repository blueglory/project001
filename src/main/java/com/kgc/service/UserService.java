package com.kgc.service;

import com.kgc.pojo.Bill;
import com.kgc.pojo.User;

import java.util.List;

public interface UserService {
    User selectByCode(String name, String password);

}
