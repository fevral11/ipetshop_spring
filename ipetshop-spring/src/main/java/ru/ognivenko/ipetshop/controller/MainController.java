package ru.ognivenko.ipetshop.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.ognivenko.ipetshop.bean.Goods;
import ru.ognivenko.ipetshop.bean.User;
import ru.ognivenko.ipetshop.dao.impl.GoodsDaoImpl;
import ru.ognivenko.ipetshop.service.GoodsService;
import ru.ognivenko.ipetshop.service.OrderService;
import ru.ognivenko.ipetshop.service.ServiceException;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/")
public class MainController {

    private static final Logger logger = Logger.getLogger(GoodsDaoImpl.class);

    private static final String ATTRIBUTE_LIST_GOODS = "listGoods";
    private static final String ATTRIBUTE_ORDER_ID = "orderId";
    private static final String ATTRIBUTE_USER = "user";
    private static final String ATTRIBUTE_GOODS_ID = "goodsId";
    private static final String ATTRIBUTE_QUANTITY_GOODS = "quantityGoods";

    private final GoodsService goodsService;
    private final OrderService orderService;

    @Autowired
    public MainController(GoodsService goodsService, OrderService orderService) {
        this.goodsService = goodsService;
        this.orderService = orderService;
    }

    @RequestMapping("/main")
    public String showHome(Model model) {

        try {
            List<Goods> listGoods = goodsService.getListGoods();
            model.addAttribute(ATTRIBUTE_LIST_GOODS, listGoods);
            return "home";
        } catch (ServiceException e) {
            logger.error("Error show list goods");
            throw new ControllerException(e);
        }
    }

    @GetMapping("/showBasket")
    public String showBasket(@SessionAttribute(name = ATTRIBUTE_ORDER_ID) Integer orderId, Model model) {
        try {
            List<Goods> listGoods = orderService.getGoodsFromOrder(orderId);
            model.addAttribute(ATTRIBUTE_LIST_GOODS, listGoods);
            return "basket";
        } catch (ServiceException e) {
            logger.error("Error show list goods from basket");
            throw new ControllerException(e);
        }
    }

    @RequestMapping("/addGoodsToBasket")
    public String addGoodsToOrder(@ModelAttribute(ATTRIBUTE_GOODS_ID) Integer goodsId,
                                  @SessionAttribute(ATTRIBUTE_USER) User user,
                                  @SessionAttribute(ATTRIBUTE_ORDER_ID) Integer orderId,
                                  HttpSession session) {
        try {
            if (orderId == 0) {
                orderId = orderService.createBasket(user);
                session.setAttribute(ATTRIBUTE_ORDER_ID, orderId);
            }
            orderService.addGoodsToOrder(goodsId, orderId);
            return "redirect:/main";
        } catch (ServiceException e) {
            logger.error("Error delete user");
            throw new ControllerException(e);
        }
    }

    @RequestMapping("/deleteGoodsInBasket")
    public String deleteGoodsInBasket(@SessionAttribute(ATTRIBUTE_ORDER_ID) Integer orderId,
                                      @RequestParam(ATTRIBUTE_GOODS_ID) Integer goodsId) {

        try {
            orderService.deleteGoodsInOrder(orderId, goodsId);
            return "redirect:/showBasket";
        } catch (ServiceException e) {
            logger.error("Error delete goods from basket");
            throw new ControllerException(e);
        }
    }
}
