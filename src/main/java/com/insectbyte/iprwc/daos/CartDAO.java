package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.dto.AddToCartDTO;
import com.insectbyte.iprwc.dto.CartDTO;
import com.insectbyte.iprwc.dto.CartItemDTO;
import com.insectbyte.iprwc.models.Cart;
import com.insectbyte.iprwc.models.Plan;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartDAO {

    private final CartRepository CART_REPOSITORY;
    private final PlanDAO PLAN_DAO;

    @Autowired
    public CartDAO (CartRepository cartRepository, PlanDAO planDAO) {
        this.CART_REPOSITORY = cartRepository;
        this.PLAN_DAO = planDAO;
    }

    public void addToCart(AddToCartDTO cartDTO, User user) {
        Plan plan = PLAN_DAO.findById(cartDTO.getProductId());
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setQuantity(cartDTO.getQuantity());
        cart.setPlan(plan);
        cart.setCreatedDate(new Date());

        CART_REPOSITORY.save(cart);
    }

    public CartDTO getCartByUserId(UUID id) {
        ArrayList<Cart> cartList = CART_REPOSITORY.getById(id);

        ArrayList<CartItemDTO> cartItems = new ArrayList<>();
        double totalCost = 0;

        for (Cart cart : cartList) {
            totalCost += (cart.getPlan().getPrice() * cart.getQuantity());
            cartItems.add(new CartItemDTO(cart));
        }

        return new CartDTO(cartItems, totalCost);
    }

    public boolean removeFromCart(UUID cartId, User user) {
        if(CART_REPOSITORY.existsById(cartId).isPresent()) {
            CART_REPOSITORY.removeFromCartById(user.getId(), cartId);
            return true;
        }
        return false;
    }

    public boolean updateQuantityById(UUID id, int value) {
        if(CART_REPOSITORY.existsById(id).isPresent()) {
            CART_REPOSITORY.updateQuantityInCartById(id, value);
            return true;
        }
        return false;
    }

    public void deleteCartByUserId(UUID id) {
        CART_REPOSITORY.removeByUserId(id);
    }
}
