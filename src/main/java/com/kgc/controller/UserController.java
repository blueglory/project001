package com.kgc.controller;

import com.kgc.dao.RoleMapper;
import com.kgc.pojo.Role;
import com.kgc.pojo.User;
import com.kgc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService service;

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("loginAfter")
    public String loginAfter(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }

    @Resource
    RoleMapper roleMapper;

    @RequestMapping(value = "dologin", method = RequestMethod.POST)
    public String dologin(HttpSession session, String userCode, String password) {
        User user = service.selectByCode(userCode, password);

        if (user == null) {
            session.setAttribute("error", "用户名或密码错误");
            return "redirect:/user/login";
        }
        Role role = new Role();
        role.setId(user.getUserrole());
        Role role1 = roleMapper.selectOne(role);
        session.setAttribute("role",role1.getRolecode());
        session.removeAttribute("error");
        session.setAttribute("user", user);
        return "redirect:/user/frame";
    }

    @RequestMapping("frame")
    public String frame() {
        return "frame";
    }
    @RequestMapping("/role")
    public String role(){
        return "/role";
    }
}
