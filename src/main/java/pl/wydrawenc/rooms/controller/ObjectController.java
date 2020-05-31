package pl.wydrawenc.rooms.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.wydrawenc.rooms.entity.Object;
import pl.wydrawenc.rooms.entity.User;
import pl.wydrawenc.rooms.service.ObjectService;
import pl.wydrawenc.rooms.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController

public class ObjectController {

    private ObjectService objectService;
    private UserService userService;

    ObjectController(ObjectService objectService, UserService userService) {
        this.objectService = objectService;
        this.userService = userService;
    }

    // REGISTER NEW OBJECT
    @PostMapping("/saveObject")
    public ModelAndView saveObject(@RequestParam String name,
                                 @RequestParam String sh_desc,
                                 @RequestParam String long_desc,
                                 @RequestParam String price_day,
                                 @RequestParam String city,
                                   @RequestParam String postal_code,
                                   @RequestParam String address,
                                   @RequestParam String imgsrc,
                                 HttpServletRequest request) {

        // create new ModelAndView to return - set the template to return after successfull/not successfull logging to index.jsp
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("show_your_objects");
        // create new User object from the data provided in register form

        HttpSession session = request.getSession();

        List<Object> objects = objectService.findAllObjects();
        modelAndView.addObject("objectList", objects);

        int userID = (int)session.getAttribute("userID");

        User owner = userService.findByUserID(userID);

        Object newObject = new Object(name,sh_desc,long_desc,Integer.parseInt(price_day),owner,city,postal_code,address,imgsrc);

        // try to save to the database and add info to the model if it worked
        try {
            objectService.saveObject(newObject);
            modelAndView.addObject("success","true");
        } catch (Exception e) {
            modelAndView.addObject("success", "false");
        }

        return modelAndView;

    }

    @GetMapping("/admin-objects")
    public ModelAndView adminObjects(HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 3) {
                List<Object> objects = objectService.findAllObjects();
                modelAndView.addObject("objectList", objects);
                modelAndView.setViewName("admin_objects");
            } else {
                modelAndView.setViewName("index");
            }
        } else {
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

    // REGISTER NEW OBJECT
    @PostMapping("/updateObject")
    public ModelAndView updateObject(
                                    @RequestParam String id,
                                    @RequestParam String name,
                                    @RequestParam String sh_desc,
                                    @RequestParam String long_desc,
                                    @RequestParam String price_day,
                                    @RequestParam String city,
                                    @RequestParam String postal_code,
                                    @RequestParam String address,
                                    @RequestParam String imgsrc,
                                    HttpServletRequest request) {


        List<Object> objects = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        if((int)request.getSession().getAttribute("usertype") == 2) {
            HttpSession session = request.getSession();
            int objectID = Integer.parseInt(id);
            objects = objectService.findObjectsByUserID(userService.findByUserID((int)session.getAttribute("userID")));
            Object updatedObject = objectService.findObjectByObjectID(objectID);
            modelAndView.setViewName("show_your_objects");
            updatedObject.setName(name);
            updatedObject.setSh_desc(sh_desc);
            updatedObject.setLong_desc(long_desc);
            updatedObject.setPrice_day(Integer.parseInt(price_day));
            updatedObject.setCity(city);
            updatedObject.setPostal_code(postal_code);
            updatedObject.setAddress(address);
            updatedObject.setImgsrc(imgsrc);
            try {
                objectService.saveObject(updatedObject);
                modelAndView.addObject("success","true");
            } catch (Exception e) {
                modelAndView.addObject("success", "false");
            }
        } else if ((int)request.getSession().getAttribute("usertype") == 3){
            HttpSession session = request.getSession();
            int objectID = Integer.parseInt(id);
            objects = objectService.findAllObjects();
            Object updatedObject = objectService.findObjectByObjectID(objectID);
            modelAndView.setViewName("admin_objects");
            updatedObject.setName(name);
            updatedObject.setSh_desc(sh_desc);
            updatedObject.setLong_desc(long_desc);
            updatedObject.setPrice_day(Integer.parseInt(price_day));
            updatedObject.setCity(city);
            updatedObject.setPostal_code(postal_code);
            updatedObject.setAddress(address);
            updatedObject.setImgsrc(imgsrc);
            try {
                objectService.saveObject(updatedObject);
                modelAndView.addObject("success","true");
            } catch (Exception e) {
                modelAndView.addObject("success", "false");
            }
        }

        modelAndView.addObject("objectList", objects);

        return modelAndView;

    }

    @GetMapping("/show-all")
    public ModelAndView list() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("show_all");

        List<Object> objects = objectService.findAllObjects();
        modelAndView.addObject("objectList", objects);

        //for(Object object : objects) {
        //    System.out.println(object.getName());
        //}

        return modelAndView;
    }

    @GetMapping("/show-your-objects")
    public ModelAndView listYourObjects(HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 2) {
                User userToSearch = userService.findByUserID((int)session.getAttribute("userID"));
                List<Object> objects = objectService.findObjectsByUserID(userToSearch);
                modelAndView.addObject("objectList", objects);
                modelAndView.setViewName("show_your_objects");
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }
        return modelAndView;
    }

    @GetMapping(value = "/details/{id}/")
    public ModelAndView getObjectDetails(
            @PathVariable("id") String id,HttpServletRequest req) {

        HttpSession session = req.getSession();
        ModelAndView modelAndView = new ModelAndView();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 1 || usertype == 2 || usertype == 3) {
                Object object = objectService.findObjectByObjectID(Integer.parseInt(id));
                modelAndView.addObject("object",object);
                modelAndView.setViewName("object_details");
            } else {
                modelAndView.setViewName("redirect:/login");
            }
        } else {
            modelAndView.setViewName("redirect:/login");
        }
        return modelAndView;
    }

    @GetMapping(value = "/delete/{id}/")
    public ModelAndView deleteObject(
            @PathVariable("id") String id, HttpServletRequest req) {

        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = req.getSession();
        List<Object> objects = new ArrayList<>();

        if (session.getAttribute("logged") != null) {
            int usertype = (int)session.getAttribute("usertype");
            if(usertype == 2 || usertype == 3) {
                if((int)req.getSession().getAttribute("usertype") == 2) {
                    int objectID = objectService.deleteObjectByObjectID(Integer.parseInt(id));

                    modelAndView.setViewName("show_your_objects");
                    User userToSearch = userService.findByUserID((int)session.getAttribute("userID"));
                    objects = objectService.findObjectsByUserID(userToSearch);
                } else if ((int)req.getSession().getAttribute("usertype") == 3){
                    int objectID = objectService.deleteObjectByObjectID(Integer.parseInt(id));
                    modelAndView.setViewName("admin_objects");
                    objects = objectService.findAllObjects();
                }
            } else {
                modelAndView.setViewName("redirect:/");
            }
        } else {
            modelAndView.setViewName("redirect:/");
        }

        modelAndView.addObject("objectList", objects);

        return modelAndView;
    }

}
