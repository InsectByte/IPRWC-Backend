package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.CartDAO;
import com.insectbyte.iprwc.daos.UserDAO;
import com.insectbyte.iprwc.dto.AddToCartDTO;
import com.insectbyte.iprwc.dto.CartDTO;
import com.insectbyte.iprwc.dto.IdDTO;
import com.insectbyte.iprwc.dto.IdValueDTO;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("${end.cart}")
@CrossOrigin(origins="${origin}")
public class CartController {

    private final CartDAO CART_DAO;
    private final UserDAO USER_DAO;
    private final JWTUtil JWT_UTIL;

    @Autowired
    public CartController (CartDAO cartDAO, JWTUtil jwtUtil, UserDAO userDAO) {
        this.CART_DAO = cartDAO;
        this.JWT_UTIL = jwtUtil;
        this.USER_DAO = userDAO;
    }

    @Secured("ROLE_USER")
    @GetMapping
    public ResponseEntity getCart(@RequestHeader Map<String, String> header) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        CartDTO cartDTO = CART_DAO.getCartByUserId(user.getId());
        return new ResponseEntity(cartDTO, HttpStatus.OK);
    }
    @Secured("ROLE_USER")
    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestHeader Map<String, String> header,
                                    @RequestBody AddToCartDTO cart) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        CART_DAO.addToCart(cart, user);

        return new ResponseEntity( HttpStatus.OK);
    }
    @Secured("ROLE_USER")
    @DeleteMapping("/remove")
    public ResponseEntity removeFromCart (@RequestHeader Map<String, String> header,
                                          @RequestBody IdDTO idDTO) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        if (CART_DAO.removeFromCart(idDTO.getId(), user)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @Secured("ROLE_USER")
    @PatchMapping("/edit")
    public ResponseEntity updateQuantityInCart(@RequestHeader Map<String, String> header,
                                               @RequestBody IdValueDTO idValueDTO) {
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(header.get("authorization").substring(7)));
        if (CART_DAO.updateQuantityById(idValueDTO.getId(), idValueDTO.getValue())) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
