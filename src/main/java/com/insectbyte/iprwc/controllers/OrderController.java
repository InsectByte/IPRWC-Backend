package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.CartDAO;
import com.insectbyte.iprwc.daos.OrderDAO;
import com.insectbyte.iprwc.daos.UserDAO;
import com.insectbyte.iprwc.dto.CartDTO;
import com.insectbyte.iprwc.dto.CartItemDTO;
import com.insectbyte.iprwc.dto.OrderDTO;
import com.insectbyte.iprwc.models.Order;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("${end.order}")
@CrossOrigin(origins = "${origin}")
public class OrderController {
    private final JWTUtil JWT_UTIL;
    private final UserDAO USER_DAO;
    private final CartDAO CART_DAO;
    private final OrderDAO ORDER_DAO;
    @Autowired
    public OrderController(JWTUtil jwtUtil, UserDAO userDAO, CartDAO cartDAO, OrderDAO orderDAO) {
        this.JWT_UTIL = jwtUtil;
        this.USER_DAO = userDAO;
        this.CART_DAO = cartDAO;
        this.ORDER_DAO = orderDAO;

    }
    @Secured("ROLE_USER")
    @PostMapping("/create")
    public ResponseEntity createOrderFromCart(@RequestHeader Map<String, String> header) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        CartDTO cartDTO = this.CART_DAO.getCartByUserId(user.getId());
        if (cartDTO.getCartItems().size() == 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ArrayList<Order> orders = new ArrayList<>();
        for (CartItemDTO cartItemDTO : cartDTO.getCartItems()) {
            for (int i = 0; i <= cartItemDTO.getQuantity(); i++) {
                orders.add(new Order(
                        cartItemDTO.getPlan(),
                        user
                ));
            }
        }
        this.ORDER_DAO.createOrders(orders);
        this.CART_DAO.deleteCartByUserId(user.getId());
        return new ResponseEntity(HttpStatus.OK);
    }
    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity getOrders(@RequestHeader Map<String, String> header) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        ArrayList<Order> orders = this.ORDER_DAO.getOrdersFromUserId(user.getId());
        if (orders.size() == 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(new OrderDTO(order.getId(), order.getPlan(), order.getCreatedDate()));
        }
        return new ResponseEntity(orderDTOS, HttpStatus.OK);
    }
    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    public ResponseEntity getAllOrders() {
        ArrayList<Order> orders = this.ORDER_DAO.getOrders();if (orders.size() == 0) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            orderDTOS.add(new OrderDTO(order.getId(), order.getPlan(), order.getCreatedDate()));
        }
        return new ResponseEntity(orderDTOS, HttpStatus.OK);
    }
}
