package ru.nikita.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import ru.nikita.dao.UserDao;
import ru.nikita.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;

@Controller

public class AppController {

    @Autowired
    UserDao dao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHelloAgain() {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String sayHelloUser() {
        return "user/index";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String newEmployee(ModelMap model) {
        User users = new User();
        model.addAttribute("users", users);

        List<User> userList = dao.getUsers();
        model.addAttribute("clients", userList);
        return "admin/index";
    }

    @RequestMapping(value = {"/admin/reg"}, method = RequestMethod.POST)
    public String saveEmployee(@Valid User users, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/index";
        }
        dao.save(users);
        return "redirect:/admin";
    }

    @RequestMapping(value = {"/admin/user/delete{idUser}-"}, method = RequestMethod.POST)
    public String delete(@ModelAttribute("idUser") String idUser, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/admin";
        }
        User user = dao.findById(Long.valueOf(idUser));
        dao.delete(user);
        return "redirect:/admin";
    }


    @RequestMapping(value = {"/admin/user/edit{idUser}-"}, method = RequestMethod.POST)
    public String edit(@ModelAttribute("idUser") String idUser, BindingResult result,
                       ModelMap model) {
        if (result.hasErrors()) {
            return "redirect:/admin";
        }
        User user = dao.findById(Long.valueOf(idUser));
        model.addAttribute("users", user);
        Map<String, String> country = new HashMap<>();
        country.put("admin", "admin");
        country.put("user", "user");
        model.addAttribute("roleList", country);
        return "admin/edit/edit";
    }

    @RequestMapping(value = {"/admin/user/update"}, method = RequestMethod.POST)
    public String update(@Valid User users, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/index";
        }
        dao.update(users);
        return "redirect:/admin";
    }


    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public String login(@ModelAttribute("login") String login, @ModelAttribute("password") String password,
                        BindingResult result,HttpServletResponse response, HttpServletRequest request) {
        User user = null;
        if (result.hasErrors()) {
            return "admin/index";
        }
        if (dao.findByLogin(login)) {
            user = dao.authorize(login);
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                switch (user.getRole()) {
                    case "user":
                        HttpSession sessionUser = request.getSession();
                        sessionUser.setAttribute("userRole", user.getRole());
                        //setting session to expiry in 30 mins
                        sessionUser.setMaxInactiveInterval(15*60);
                        Cookie userName = new Cookie("userName", user.getName());
                        userName.setMaxAge(15*60);
                        response.addCookie(userName);
                        return "redirect:/user";
                    case "admin":
                        HttpSession sessionAdmin = request.getSession();
                        sessionAdmin.setAttribute("userRole", user.getRole());
                        //setting session to expiry in 30 mins
                        sessionAdmin.setMaxInactiveInterval(15*60);
                        Cookie adminName = new Cookie("userName", user.getName());
                        adminName.setMaxAge(15*60);
                        response.addCookie(adminName);
                        return "redirect:/admin";
                }
            } else {
                return "index";
            }
        } else {
            return "index";
        }
        return "index";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }
}
