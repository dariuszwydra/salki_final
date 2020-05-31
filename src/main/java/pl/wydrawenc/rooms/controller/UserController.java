package pl.wydrawenc.rooms.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.wydrawenc.rooms.entity.Object;
import pl.wydrawenc.rooms.entity.User;
import pl.wydrawenc.rooms.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER NEW USER
    @PostMapping("/saveUser")
    public ModelAndView saveUser(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String usertype,
                                 HttpServletRequest request) {

        // create new ModelAndView to return - set the template to return after successfull/not successfull logging to index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        // create new User object from the data provided in register form
        int usertypeToSave = 1;
        if (usertype.equals("user")) {
            usertypeToSave = 1;
        } else if (usertype.equals("tenant")) {
            usertypeToSave = 2;
        }
        User newUser = new User(username,password,firstName,lastName,usertypeToSave);
        // try to save to the database and add info to the model if it worked
        try {
            userService.saveUser(newUser);
            modelAndView.addObject("success","true");
        } catch (Exception e) {
            modelAndView.addObject("success", "false");
        }

        return modelAndView;

    }

    // Change Username
    @PostMapping("/username-change")
    public ModelAndView updateUsername(@RequestParam String username,
                                 HttpServletRequest request) {

        // create new ModelAndView to return - set the template to return after successfull/not successfull logging to index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("username_change");

        HttpSession session = request.getSession();

        String loggedUsername = (String)session.getAttribute("username");

        User user = userService.findByUsername(loggedUsername);

        user.setUsername(username);
        session.setAttribute("username",username);

        try {
            userService.saveUser(user);
            modelAndView.addObject("success","true");
        } catch (Exception e) {
            modelAndView.addObject("success", "false");
        }

        return modelAndView;

    }

    // Change Username
    @PostMapping("/password-change")
    public ModelAndView updatePassword(@RequestParam String password,
                                       @RequestParam String password2,
                                       HttpServletRequest request) {

        // create new ModelAndView to return - set the template to return after successfull/not successfull logging to index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("password_change");

        HttpSession session = request.getSession();

        String loggedUsername = (String)session.getAttribute("username");

        User user = userService.findByUsername(loggedUsername);

        if(password.equals(password2)) {
            user.setPassword(password);
            try {
                userService.saveUser(user);
                modelAndView.addObject("success","true");
            } catch (Exception e) {
                modelAndView.addObject("success", "false");
            }
        } else {
                modelAndView.addObject("valid", "false");
        }

        return modelAndView;

    }

    // LOG-IN
    @PostMapping("/login-confirm")
    public ModelAndView loginUser(HttpServletRequest request,
                                  @RequestParam String username,
                                  @RequestParam String password,
                                  HttpSession session) {

        // create new ModelAndView to return - set the template to return after successfull/not successfull logging to index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        // get session ready
        session = request.getSession();
        // try to find user with the data provided from form
        User user = userService.findByUsernameAndPassword(username,password);
        // if success, set that he is logged and set his username/id in session.
        if (user != null) {
            session.setAttribute("logged", true);
            session.setAttribute("username",user.getUsername());
            session.setAttribute("userID",user.getUserID());
            session.setAttribute("usertype",user.getUsertype());
        }

        return modelAndView;

    }

    @GetMapping("/show-all-users")
    public ModelAndView listUsers(HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 3) {
                List<User> users = userService.findAllUsers();
                modelAndView.addObject("userList", users);
                modelAndView.setViewName("show_all_users");
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;
    }

    @GetMapping("/admin-users")
    public ModelAndView adminUsers(HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 3) {
                List<User> users = userService.findAllUsers();
                modelAndView.addObject("userList", users);
                modelAndView.setViewName("admin_users");
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        return modelAndView;

    }

    // LOG-OUT
    @GetMapping("/logout-confirm")
    public ModelAndView logoutUser(HttpServletRequest request, HttpSession session) {

        // create new ModelAndView to return - set the template to return index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        session.invalidate();

        return modelAndView;
    }

    @GetMapping(value = "/deleteUser/{id}/")
    public ModelAndView deleteUser(
            @PathVariable("id") String id, HttpServletRequest req) {

        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession();
        List<User> users = new ArrayList<>();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 3) {
                    userService.deleteUserByID(Integer.parseInt(id));
                    modelAndView.setViewName("admin_users");
                    users = userService.findAllUsers();

            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        modelAndView.addObject("userList", users);

        return modelAndView;
    }


    @PostMapping("/updateUser")
    public ModelAndView updateUser(
            @RequestParam String id,
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String usertype,
            HttpServletRequest request) {


        List<User> users = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();

            HttpSession session = request.getSession();
            int userID = Integer.parseInt(id);
            User updatedUser = userService.findByUserID(userID);
            modelAndView.setViewName("admin_users");
            updatedUser.setUsername(username);
            updatedUser.setPassword(password);
            updatedUser.setFirstName(firstName);
            updatedUser.setLastName(lastName);
            updatedUser.setUsertype(Integer.parseInt(usertype));
            try {
                userService.saveUser(updatedUser);
                modelAndView.addObject("success","true");
            } catch (Exception e) {
                modelAndView.addObject("success", "false");
            }


        modelAndView.addObject("userList", users);

        return modelAndView;

    }
}
