package ru.ognivenko.ipetshop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.bean.UserAuthority;
import ru.ognivenko.ipetshop.dao.impl.UserDaoImpl;
import ru.ognivenko.ipetshop.service.ServiceException;
import ru.ognivenko.ipetshop.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_USER_AUTHORITY = "userAuthority";
    private static final String ATTRIBUTE_ORDER_ID = "orderId";
    private static final Integer orderId=0;

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String authorisation(@ModelAttribute(ATTRIBUTE_USER_AUTHORITY) UserAuthority userAuthority,
                                HttpSession httpSession) {
        try {
            User user = userService.authorization(userAuthority);
            httpSession.setAttribute(ATTRIBUTE_USER, user);
            httpSession.setAttribute(ATTRIBUTE_ORDER_ID, orderId);
            return "redirect:/main";
        } catch (ServiceException e) {
            logger.error("Error login user");
            throw new ControllerException(e);
        }
    }

    @GetMapping("/authorization")
    public String authorization(Model model) {
        UserAuthority userAuthority = new UserAuthority();
        model.addAttribute(ATTRIBUTE_USER_AUTHORITY, userAuthority);
        return "login";
    }

    @PostMapping("/saveUser")
    public String addUser(@ModelAttribute(ATTRIBUTE_USER) User user, HttpSession httpSession) {
        try {
            userService.addUser(user);
            httpSession.setAttribute(ATTRIBUTE_USER, user);
            httpSession.setAttribute(ATTRIBUTE_ORDER_ID, orderId);
            return "redirect:/main";
        } catch (ServiceException e) {
            logger.error("Error save user");
            throw new ControllerException(e);
        }
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@SessionAttribute(ATTRIBUTE_USER) User user, HttpSession httpSession) {
        try {
            userService.deleteUser(user);
            httpSession.invalidate();
            return "redirect:/main";
        } catch (ServiceException e) {
            logger.error("Error delete user");
            throw new ControllerException(e);
        }
    }

    @GetMapping("/showFormForAddUser")
    public String showFormForAdd(Model model) {
        User user = new User();
        model.addAttribute(ATTRIBUTE_USER, user);
        return "user-form";
    }
}
