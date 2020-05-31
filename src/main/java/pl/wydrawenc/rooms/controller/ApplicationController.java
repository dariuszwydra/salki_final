package pl.wydrawenc.rooms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.wydrawenc.rooms.entity.Object;
import pl.wydrawenc.rooms.entity.User;
import pl.wydrawenc.rooms.service.ObjectService;
import pl.wydrawenc.rooms.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ApplicationController {

    UserService userService;
    ObjectService objectService;

    public ApplicationController (UserService userService, ObjectService objectService) {
        this.userService = userService;
        this.objectService = objectService;
    }

    @RequestMapping("/")
    public String home() {
        return "index";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/add-object")
    public String addObject(HttpServletRequest req) {

        HttpSession session = req.getSession();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 2 || usertype == 3) {
                return "add_object";
            } else {
                return "redirect:/";
            }
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping("/user-settings")
    public String userSettings(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            return "user_settings";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping("/username-change")
    public String usernameChange(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            return "username_change";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping("/password-change")
    public String passwordChange(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("logged") != null) {
            return "password_change";
        } else {
            return "redirect:/";
        }

    }

    @RequestMapping(value = "/edit/{id}/")
    public ModelAndView editObject(
            @PathVariable("id") String id, HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 2 || usertype == 3) {
                Object object = objectService.findObjectByObjectID(Integer.parseInt(id));
                modelAndView.addObject("object",object);
                modelAndView.setViewName("edit_object");
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;

    }

    @RequestMapping(value = "/editUser/{id}/")
    public ModelAndView editUser(
            @PathVariable("id") String id, HttpServletRequest req) {


        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 3) {
                User user = userService.findByUserID(Integer.parseInt(id));
                modelAndView.addObject("user",user);
                modelAndView.setViewName("edit_user");
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;

    }

}
